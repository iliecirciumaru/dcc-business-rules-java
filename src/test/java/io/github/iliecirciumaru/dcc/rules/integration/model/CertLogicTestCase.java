package io.github.iliecirciumaru.dcc.rules.integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import java.util.List;

@AutoValue
@JsonDeserialize(builder = AutoValue_CertLogicTestCase.Builder.class)
public abstract class CertLogicTestCase {

  public static Builder builder() {
    return new AutoValue_CertLogicTestCase.Builder();
  }

  @JsonProperty("name")
  public abstract String getName();

  @JsonProperty("certLogicExpression")
  public abstract Object getCertLogicExpression();

  @JsonProperty("assertions")
  public abstract List<CertLogicTestAssertion> getAssertions();

  @AutoValue.Builder
  public abstract static class Builder {

    @JsonProperty("name")
    public abstract Builder setName(String newName);

    @JsonProperty("certLogicExpression")
    public abstract Builder setCertLogicExpression(Object newCertLogicExpression);

    @JsonProperty("assertions")
    public abstract Builder setAssertions(List<CertLogicTestAssertion> newAssertions);

    public abstract CertLogicTestCase build();
  }
}
