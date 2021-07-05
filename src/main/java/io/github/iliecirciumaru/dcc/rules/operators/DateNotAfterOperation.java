package io.github.iliecirciumaru.dcc.rules.operators;

import io.github.jamsesso.jsonlogic.evaluator.JsonLogicEvaluationException;
import java.time.ZonedDateTime;
import java.util.List;

public class DateNotAfterOperation extends BaseDateOperation {
  @Override
  public String key() {
    return "not-after";
  }

  @Override
  public Object evaluate(List arguments, Object data) throws JsonLogicEvaluationException {
    if (arguments.size() < 2) {
      throw new JsonLogicEvaluationException("Operation evaluates minimum two values");
    }

    Object firstOp = arguments.get(0);
    Object secondOp = arguments.get(1);
    Object thirdOp = arguments.size() == 3 ? arguments.get(2) : null;

    if (firstOp instanceof Double && secondOp instanceof Double) {
      boolean result = (Double) firstOp <= (Double) secondOp;
      if (thirdOp != null) {
        return result && (Double) secondOp <= (Double) thirdOp;
      }
    }

    try {
      ZonedDateTime firstDate = this.parse(firstOp);
      ZonedDateTime secondDate = this.parse(secondOp);

      boolean result = !firstDate.isAfter(secondDate);
      if (thirdOp != null) {
        ZonedDateTime thirdDate = this.parse(thirdOp);
        result = result && (!secondDate.isAfter(thirdDate));
      }

      return result;
    } catch (Exception e) {
      throw new JsonLogicEvaluationException("Exception during execution of 'earlier' operation", e);
    }
  }
}
