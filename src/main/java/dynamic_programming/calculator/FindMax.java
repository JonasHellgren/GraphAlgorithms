package dynamic_programming.calculator;

import java.util.List;

public class FindMax implements Strategy{

    @Override
    public double findBest(List<Double> numbers) {
        return  numbers.stream().mapToDouble(Double::doubleValue).max().getAsDouble();
    }
}
