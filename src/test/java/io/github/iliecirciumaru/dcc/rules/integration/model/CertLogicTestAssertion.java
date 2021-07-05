package io.github.iliecirciumaru.dcc.rules.integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import javax.annotation.Nullable;

@AutoValue
@JsonDeserialize(builder = AutoValue_CertLogicTestAssertion.Builder.class)
public abstract class CertLogicTestAssertion {
  @JsonProperty("data")
  @Nullable
  public abstract Object getData();

  @JsonProperty("expected")
  @Nullable
  public abstract Object getExpected();

  @JsonProperty("message")
  @Nullable
  public abstract String getMessage();

  @JsonProperty("directive")
  @Nullable
  public abstract String getDirective();

  public static Builder builder() {
    return new AutoValue_CertLogicTestAssertion.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {
    @JsonProperty("data")
    public abstract Builder setData(@Nullable Object newData);

    @JsonProperty("expected")
    public abstract Builder setExpected(@Nullable Object newExpected);

    @JsonProperty("message")
    public abstract Builder setMessage(@Nullable String newMessage);

    @JsonProperty("directive")
    public abstract Builder setDirective(@Nullable String newDirective);

    public abstract CertLogicTestAssertion build();
  }
}
