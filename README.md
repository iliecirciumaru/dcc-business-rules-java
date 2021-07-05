### Description

This repository contains code of the customized Java Rules Engine for European Digital Covid Certificate.

It is based on the Java json-logic engine by [jamsesso](https://github.com/jamsesso/json-logic-java/)

### How to run

- Instantiate `Rule Engine` from factory `DCCRuleEngineFactory`
- Execute rules on data

```
JsonLogic ruleEngine = DCCRuleEngineFactory.getInstance();
Object result = ruleEngine.apply(rule, data);
```

