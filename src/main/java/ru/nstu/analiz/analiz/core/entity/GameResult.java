package ru.nstu.analiz.analiz.core.entity;

import lombok.Builder;

@Builder
public record GameResult(
        int win,
        int lastGameTurnNumber
) {

}
