package io.github.iliecirciumaru.dcc.rules.operators;

import io.github.iliecirciumaru.dcc.rules.utils.RFC339DateSerializer;
import io.github.jamsesso.jsonlogic.evaluator.JsonLogicEvaluationException;
import io.github.jamsesso.jsonlogic.evaluator.expressions.PreEvaluatedArgumentsExpression;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class SetTimeOperation implements PreEvaluatedArgumentsExpression {

  private final DateTimeFormatter DEFAULT_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ").withZone(
      ZoneId.of("UTC"));

  @Override
  public String key() {
    return "setTime";
  }

  @Override
  public Object evaluate(List arguments, Object data) throws JsonLogicEvaluationException {
    if (arguments.size() < 3) {
      throw new JsonLogicEvaluationException("Operation evaluates minimum 3 arguments");
    }

    try {
      ZonedDateTime baseTime;

      // can be extended for more use-cases
      if (arguments.get(0) instanceof String && arguments.get(0).equals("now")) {
        baseTime = ZonedDateTime.now();
      } else if (arguments.get(0) instanceof String) {
        baseTime = ZonedDateTime.parse((String) arguments.get(0));
      } else {
        Instant epochTime = Instant.ofEpochSecond((Long) arguments.get(0));
        baseTime = ZonedDateTime.ofInstant(epochTime, ZoneId.of("UTC"));
      }

      long amount = Math.round((Double) arguments.get(1));
      String unit = (String) arguments.get(2);
      String format = arguments.size() == 4 ? ((String) arguments.get(3)).toUpperCase() : "RFC3339";

      ChronoUnit timeUnit = ChronoUnit.DAYS;

      switch (unit) {
        case "s":
          timeUnit = ChronoUnit.SECONDS;
          break;
        case "m":
          timeUnit = ChronoUnit.MINUTES;
          break;
        case "h":
        case "hour":
          timeUnit = ChronoUnit.HOURS;
          break;
        case "d":
        case "day":
          timeUnit = ChronoUnit.DAYS;
          break;
        case "M":
          timeUnit = ChronoUnit.MONTHS;
          break;
        case "Y":
          timeUnit = ChronoUnit.YEARS;
          break;
      }

      ZonedDateTime calculatedTime = baseTime.plus(amount, timeUnit);

      Object result;

      switch (format) {
        case "posix":
          result = (double) calculatedTime.toEpochSecond();
          break;
        case "RFC3339" :
          result = RFC339DateSerializer.formatString(calculatedTime);
          break;
        default:
          result = DEFAULT_TIME_FORMAT.format(calculatedTime);
          break;
      }

      return result;
    } catch (Exception e) {
      throw new JsonLogicEvaluationException("Exception during execution of 'setTime' operation",
          e);
    }
  }
}
