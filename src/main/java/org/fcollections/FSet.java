package org.fcollections;

import org.pcollections.PSet;

import java.util.Collection;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public interface FSet<E> extends PSet<E> {

  @Override FSet<E> plus(E e);
  @Override FSet<E> plusAll(Collection<? extends E> list);
  @Override FSet<E> minus(Object e);
  @Override FSet<E> minusAll(Collection<?> list);

  E head();
  FSet<E> tail();
  FSet<E> filter(Predicate<? super E> predicate);
  <R> FSet<R> map(Function<? super E, ? extends R> mapFunction);
  E reduce(E initial, BinaryOperator<E> reduceOp);
  Optional<E> reduce(BinaryOperator<E> reduceOp);

  @Override
  @Deprecated
  default boolean removeIf(Predicate<? super E> predicate) {
    throw new UnsupportedOperationException();
  }
}
