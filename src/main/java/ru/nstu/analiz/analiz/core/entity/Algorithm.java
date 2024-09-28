package ru.nstu.analiz.analiz.core.entity;

import lombok.Builder;

import java.util.List;

@Builder
public class Algorithm {

    private List<Integer> steps;

    public int getNextStep() {
        int first = steps.getFirst();
        if (first > 0) {
            steps.set(0, first - 1);
            return 1;
        } else if (first < 0) {
            steps.set(0, first + 1);
            return -1;
        } else {
            steps.removeFirst();
            return getNextStep();
        }
    }

    public int getAlgorithmSize() {
        return steps.stream().
                map(Math::abs).
                reduce(0, Integer::sum);
    }
}
