package dynamic_programming.calculator;

import java.util.List;
import java.util.logging.Logger;

public interface  Strategy {

    double BEST_IF_EMPTY_LIST=0d;
    Logger logger = Logger.getLogger(Strategy.class.getName());

    default void logMessageIfEmptyList(List<Double> numbers) {
        if (numbers.isEmpty()) {
            logger.warning("Finding min or max of empty list");
        }
    }

    double findBest(List<Double> numbers);
}
