package org.fcollections;

import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class FSetTest {

  @Test
  public void empty() {
    FSet<Integer> set = FSet.empty();
    assertThat(set).isEmpty();
  }

  @Test
  public void of() {
    FSet<Integer> set = FSet.of(1, 2);
    assertThat(set).containsExactly(1, 2);
  }

  @Test
  public void plus() {
    assertThat(FSet.of(1, 2).plus(3)).containsExactly(1, 2, 3);
  }

  @Test
  public void minus() {
    assertThat(FSet.of(1, 2, 3).minus(2)).containsExactly(1, 3);
  }

  @Test
  public void plusAll() {
    assertThat(FSet.of(2).plusAll(Arrays.asList(1, 3)))
      .containsExactly(1, 2, 3);
  }

  @Test
  public void minusAll() {
    assertThat(FSet.of(1, 2, 3).minusAll(Arrays.asList(1, 2)))
      .containsExactly(3);
  }

  @Test
  public void headTail() {
    FSet<Integer> set = FSet.of(1, 2, 3);
    assertThat(set).contains(set.head());
    assertThat(set.tail()).doesNotContain(set.head());
  }

  @Test
  public void filter() {
    assertThat(FSet.of(1, 2, 3, 4, 5, 6).filter(x -> x % 2 == 0))
      .containsExactly(2, 4, 6);
  }

  @Test
  public void map() {
    assertThat(FSet.of(1, 2, 3).map(x -> x * 2)).containsExactly(2, 4, 6);
  }

  @Test
  public void reduce() {
    assertThat(FSet.of(1, 2, 3, 4, 5).reduce(0, (a, b) -> a + b))
      .isEqualTo(15);
  }

  @Test
  public void reduceOptional() {
    assertThat(FSet.of(1, 2, 3, 4, 5).reduce((a, b) -> a + b))
      .isEqualTo(Optional.of(15));
  }
}
