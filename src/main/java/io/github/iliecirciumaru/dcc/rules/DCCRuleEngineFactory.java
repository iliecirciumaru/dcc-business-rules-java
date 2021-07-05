package io.github.iliecirciumaru.dcc.rules;

import io.github.iliecirciumaru.dcc.rules.operators.DateAfterOperation;
import io.github.iliecirciumaru.dcc.rules.operators.DateBeforeOperation;
import io.github.iliecirciumaru.dcc.rules.operators.DateNotAfterOperation;
import io.github.iliecirciumaru.dcc.rules.operators.DateNotBeforeOperation;
import io.github.iliecirciumaru.dcc.rules.operators.PlusTimeOperation;
import io.github.jamsesso.jsonlogic.JsonLogic;

public class DCCRuleEngineFactory {
  public static JsonLogic getInstance() {
    JsonLogic ruleEngine = new JsonLogic();
    ruleEngine.addOperation(new PlusTimeOperation());
    ruleEngine.addOperation(new DateBeforeOperation());
    ruleEngine.addOperation(new DateNotBeforeOperation());
    ruleEngine.addOperation(new DateAfterOperation());
    ruleEngine.addOperation(new DateNotAfterOperation());

    return ruleEngine;
  }
}
