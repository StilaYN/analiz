package ru.nstu.analiz.analiz.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record StatisticResponse(
    @Schema(description = "Вероятность победы")
    float winRate,
    @Schema(description = "Средняя длина партии")
    float middleGameLength
) {

}
