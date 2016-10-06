package org.fcollections;

import java.util.Collection;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public interface FCollection<E> extends Collection<E> {
  E head();
  FCollection<E> tail();
  FCollection<E> plus(E e);
  FCollection<E> plusAll(Collection<? extends E> elements);
  FCollection<E> minus(Object e);
  FCollection<E> minusAll(Collection<?> list);
  <R> FCollection<R> map(Function<? super E, ? extends R> mapFunction);

  @Override
  @Deprecated
  default boolean removeIf(Predicate<? super E> predicate) {
    throw new UnsupportedOperationException();
  }

  default E reduce(E initial, BinaryOperator<E> reduceOp) {
    E acc = initial;
    for (E e : this) {
      acc = reduceOp.apply(acc, e);
    }
    return acc;
  }

  default Optional<E> reduce(BinaryOperator<E> reduceOp) {
    if (isEmpty()) {
      return Optional.empty();
    }
    E acc = head();
    for (E e : tail()) {
      acc = reduceOp.apply(acc, e);
    }
    return Optional.of(acc);
  }

  default  <A> A fold(A initial, BiFunction<A, ? super E, A> foldOp) {
    A acc = initial;
    for (E e : this) {
      acc = foldOp.apply(acc, e);
    }
    return acc;
  }
}
