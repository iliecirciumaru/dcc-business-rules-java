package io.github.iliecirciumaru.dcc.rules.integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import java.util.List;
import javax.annotation.Nullable;

@AutoValue
@JsonDeserialize(builder = AutoValue_CertLogicTestSuite.Builder.class)
public abstract class CertLogicTestSuite {
  @JsonProperty("name")
  public abstract String getName();

  @JsonProperty("skip")
  @Nullable
  public abstract String getDirective();

  @JsonProperty("cases")
  public abstract List<CertLogicTestCase> getCases();

  public static Builder builder() {
    return new AutoValue_CertLogicTestSuite.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {

    @JsonProperty("name")
    public abstract Builder setName(String newName);

    @JsonProperty("directive")
    public abstract Builder setDirective(@Nullable String newDirective);

    @JsonProperty("cases")
    public abstract Builder setCases(List<CertLogicTestCase> newCases);

    public abstract CertLogicTestSuite build();
  }
}
