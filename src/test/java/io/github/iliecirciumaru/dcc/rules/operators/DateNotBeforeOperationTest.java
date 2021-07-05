package io.github.iliecirciumaru.dcc.rules.operators;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class DateNotBeforeOperationTest extends MockDateFactory {
  private DateNotBeforeOperation subject = new DateNotBeforeOperation();

  @Test
  public void happyPath() throws Exception {
    List<String> timestamps = List.of(may5, may4);
    Object actual = this.subject.evaluate(timestamps, null);
    assertThat(actual).isEqualTo(true);

    timestamps = List.of(may5, may5);
    actual = this.subject.evaluate(timestamps, null);
    assertThat(actual).isEqualTo(true);

    timestamps = List.of(may5, may4, may3);
    actual = this.subject.evaluate(timestamps, null);
    assertThat(actual).isEqualTo(true);

    timestamps = List.of(may5, may4date);
    actual = this.subject.evaluate(timestamps, null);
    assertThat(actual).isEqualTo(true);

    timestamps = List.of(may5date, may4, may3date);
    actual = this.subject.evaluate(timestamps, null);
    assertThat(actual).isEqualTo(true);

    timestamps = List.of(may5date, may5date, may5date);
    actual = this.subject.evaluate(timestamps, null);
    assertThat(actual).isEqualTo(true);

    timestamps = List.of(may5, may5, may5);
    actual = this.subject.evaluate(timestamps, null);
    assertThat(actual).isEqualTo(true);
  }

  @Test
  public void failedPath() throws Exception {
    List<String> timestamps = List.of(may4, may5);
    Object actual = this.subject.evaluate(timestamps, null);
    assertThat(actual).isEqualTo(false);

    timestamps = List.of(may4date, may5);
    actual = this.subject.evaluate(timestamps, null);
    assertThat(actual).isEqualTo(false);

    timestamps = List.of(may3date, may4, may5date);
    actual = this.subject.evaluate(timestamps, null);
    assertThat(actual).isEqualTo(false);

    timestamps = List.of(may3date, may4, may3);
    actual = this.subject.evaluate(timestamps, null);
    assertThat(actual).isEqualTo(false);
  }
}
