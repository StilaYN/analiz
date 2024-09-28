package ru.nstu.analiz.analiz.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record StatisticRequest(
        @Schema(description = "Последовательность шагов для алгоритма", requiredMode = Schema.RequiredMode.REQUIRED)
        List<Integer> algorithm,
        @Schema(description = "Вероятность что подсказка дана к центру", requiredMode = Schema.RequiredMode.REQUIRED)
        float centerDirectionRate,
        @Schema(description = "Количество ходов в партии", requiredMode = Schema.RequiredMode.REQUIRED)
        int gameLength,
        @Schema(description = "Количество проведенных экспериментов", requiredMode = Schema.RequiredMode.REQUIRED)
        int experimentNumber
) {

}
