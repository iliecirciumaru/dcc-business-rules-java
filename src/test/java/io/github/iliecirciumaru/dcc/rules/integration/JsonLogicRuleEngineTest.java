package io.github.iliecirciumaru.dcc.rules.integration;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.iliecirciumaru.dcc.rules.DCCRuleEngineFactory;
import io.github.iliecirciumaru.dcc.rules.integration.model.CertLogicTestAssertion;
import io.github.iliecirciumaru.dcc.rules.integration.model.CertLogicTestCase;
import io.github.iliecirciumaru.dcc.rules.integration.model.CertLogicTestSuite;
import io.github.jamsesso.jsonlogic.JsonLogic;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JsonLogicRuleEngineTest {
  ObjectMapper mapper = new ObjectMapper();
  JsonLogic ruleEngine;

  @BeforeEach
  public void init() {
    ruleEngine = DCCRuleEngineFactory.getInstance();
  }

  @Test
  public void happyTestSuite() throws Exception {
    URL resource = getClass().getClassLoader().getResource("certlogic/testsuite");
    System.out.println(resource);

    File directory = Paths.get(resource.toURI()).toFile();
    Path path = directory.toPath();

    for (String testFile : directory.list()) {
      CertLogicTestSuite suite = mapper.readValue(path.resolve(testFile).toFile(), CertLogicTestSuite.class);

      if (suite.getDirective() != null && suite.getDirective().equals("skip")) {
        continue;
      }

      for (CertLogicTestCase test: suite.getCases()) {
        String rule = mapper.writeValueAsString(test.getCertLogicExpression());
        for (CertLogicTestAssertion assertion: test.getAssertions()) {
          if (assertion.getDirective() != null && assertion.getDirective().equals("skip")) {
            continue;
          }
          try {
            Object actual = this.execute(rule, assertion.getData());
            assertThat(normalize(actual)).isEqualTo(normalize(assertion.getExpected()));
          } catch (Exception e) {
            System.out.println("Test failed: " + test.getName());
            System.out.println("Test expression: " + test.getCertLogicExpression());
            throw e;
          }
        }
      }
    }
  }

  private Object normalize(Object value) {
    if (value instanceof Integer) {
      return ((Integer) value).doubleValue();
    }

    return value;
  }

  private Object execute(String rule, Object data) throws Exception {
    try {
      return this.ruleEngine.apply(rule, data);
    } catch (IndexOutOfBoundsException e) {
      e.printStackTrace();
      return null;
    }
  }
}

