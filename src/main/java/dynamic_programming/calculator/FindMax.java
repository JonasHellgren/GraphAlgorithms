package dynamic_programming.calculator;

import java.util.List;
import java.util.OptionalDouble;

public class FindMax implements Strategy {

    @Override
    public double findBest(List<Double> numbers) {
        logMessageIfEmptyList(numbers);
        OptionalDouble max = numbers.stream().mapToDouble(v -> v).max();
        return max.orElse(BEST_IF_EMPTY_LIST);
    }
}
