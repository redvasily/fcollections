package org.fcollections;

import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class FVectorTest {

  @Test
  public void empty() {
    FVector<Integer> vector = FVector.empty();
    assertThat(vector).isEmpty();
  }

  @Test
  public void of() {
    FVector<Integer> vector = FVector.of(1, 2);
    assertThat(vector).containsExactly(1, 2);
  }

  @Test
  public void plus() {
    assertThat(FVector.of(1, 2).plus(3)).containsExactly(1, 2, 3);
  }

  @Test
  public void plusPos() {
    assertThat(FVector.of(1, 2).plus(0, 3)).containsExactly(3, 1, 2);
  }

  @Test
  public void minus() {
    assertThat(FVector.of(1, 2, 3).minus(Integer.valueOf(2))).containsExactly(1, 3);
  }

  @Test
  public void minusPos() {
    assertThat(FVector.of(1, 2, 3).minus(0)).containsExactly(2, 3);
  }

  @Test
  public void plusAll() {
    assertThat(FVector.of(1).plusAll(Arrays.asList(2, 3))).containsExactly(1, 2, 3);
  }

  @Test
  public void minusAll() {
    assertThat(FVector.of(1, 2, 3).minusAll(Arrays.asList(2, 3))).containsExactly(1);
  }

  @Test
  public void headTail() {
    FVector<Integer> vector = FVector.of(1, 2, 3);
    assertThat(vector.get(0)).isEqualTo(vector.head());
    assertThat(vector.tail()).containsExactly(2, 3);
  }

  @Test
  public void filter() {
    assertThat(FVector.of(1, 2, 3, 4).filter(x -> x % 2 == 0))
      .containsExactly(2, 4);
  }

  @Test
  public void map() {
    assertThat(FVector.of(1, 2).map(x -> x * 2)).containsExactly(2, 4);
  }

  @Test
  public void reduce() {
    assertThat(FVector.of(1, 2, 3, 4, 5).reduce(0, (a, b) -> a + b))
      .isEqualTo(15);
  }

  @Test
  public void reduceOptional() {
    assertThat(FVector.of(1, 2, 3, 4, 5).reduce((a, b) -> a + b))
      .isEqualTo(Optional.of(15));
  }
}
