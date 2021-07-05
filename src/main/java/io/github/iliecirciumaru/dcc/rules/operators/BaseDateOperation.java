package io.github.iliecirciumaru.dcc.rules.operators;

import io.github.jamsesso.jsonlogic.evaluator.JsonLogicEvaluationException;
import io.github.jamsesso.jsonlogic.evaluator.expressions.PreEvaluatedArgumentsExpression;
import java.time.ZonedDateTime;

public abstract class BaseDateOperation implements PreEvaluatedArgumentsExpression {
  public ZonedDateTime parse(Object timestamp) throws JsonLogicEvaluationException {
    if (!(timestamp instanceof String)) {
      throw new JsonLogicEvaluationException("Only string timestamps are allowed to be parsed, got: " + timestamp);
    }

    // if only date passed, then for parsing scope will be added time
    // date: YYYY-MM-dd
    if (((String) timestamp).length() == 10) {
      timestamp += "T00:00:00+00:00";
    }

    return ZonedDateTime.parse((String) timestamp);
  }
}
