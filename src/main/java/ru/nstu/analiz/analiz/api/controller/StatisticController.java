package ru.nstu.analiz.analiz.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nstu.analiz.analiz.api.model.StatisticRequest;
import ru.nstu.analiz.analiz.api.model.StatisticResponse;
import ru.nstu.analiz.analiz.core.service.StatisticService;

@RestController
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    @PostMapping("/statistic")
    StatisticResponse getStatistic(@RequestBody StatisticRequest request) {
        return statisticService.getStatistic(request);
    }

}
