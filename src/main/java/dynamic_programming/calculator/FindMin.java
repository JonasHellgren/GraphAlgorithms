package dynamic_programming.calculator;

import java.util.List;
import java.util.OptionalDouble;

public class FindMin implements Strategy{
    @Override
    public double findBest(List<Double> numbers) {
        logMessageIfEmptyList(numbers);
        OptionalDouble min = numbers.stream().mapToDouble(v -> v).min();
        return min.orElse(BEST_IF_EMPTY_LIST);
    }
}
