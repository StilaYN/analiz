package ru.nstu.analiz.analiz.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nstu.analiz.analiz.api.model.StatisticRequest;
import ru.nstu.analiz.analiz.api.model.StatisticResponse;
import ru.nstu.analiz.analiz.core.entity.Algorithm;
import ru.nstu.analiz.analiz.core.entity.GameResult;
import ru.nstu.analiz.analiz.core.exception.ModelException;

import java.util.ArrayList;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticService {

    private final Random random = new Random();

    public StatisticResponse getStatistic(StatisticRequest request) {
        int winGameCount = 0;
        int gameLengthCount = 0;
        log.info(request.toString());
        for (int i = 0; i < request.experimentNumber(); i++) {
            GameResult gameResult = playGame(
                    Algorithm.builder().steps(new ArrayList<>(request.algorithm())).build(),
                    request.centerDirectionRate(),
                    request.gameLength()
            );
            winGameCount += gameResult.win();
            gameLengthCount += gameResult.lastGameTurnNumber();
        }
        log.info("Win Game Count: {}, Game Length Count: {}", winGameCount, gameLengthCount);
        return StatisticResponse.builder()
                .winRate((float) winGameCount / request.experimentNumber())
                .middleGameLength((float) gameLengthCount / request.experimentNumber())
                .build();
    }

    private GameResult playGame(Algorithm algorithm, float centerDirectionRate, int gameLength) {
        int currentLevel = 1;
        int currentTurn = 1;
        int win = 1;
        if(algorithm.getAlgorithmSize()<gameLength){
            throw new ModelException("Алгоритм не может быть короче чем длина партии");
        }
        while(currentTurn<gameLength){
            currentTurn++;
            int hint = generateHint(centerDirectionRate);
            int step = algorithm.getNextStep();
            currentLevel += hint * step;
            if(currentLevel<1){
                currentLevel = 1;
            }
            if(currentLevel>5){
                win = 0;
                break;
            }
        }
        return GameResult.builder()
                .win(win)
                .lastGameTurnNumber(currentTurn)
                .build();
    }

    private int generateHint(float centerDirectionRate) {
        float hint = random.nextFloat();
        if (hint < centerDirectionRate) {
            return -1;
        } else {
            return 1;
        }
    }
}
