package dynamic_programming.calculator;

import java.util.List;

public class FindMin implements Strategy{
    @Override
    public double findBest(List<Double> numbers) {
        return numbers.stream().mapToDouble(Double::doubleValue).min().getAsDouble();
    }
}
