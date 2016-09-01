package org.fcollections;

import org.pcollections.PVector;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.*;

public interface FVector<E> extends PVector<E> {
  E head();

  FVector<E> tail();

  FVector<E> plus(E e);

  FVector<E> plusAll(Collection<? extends E> list);

  FVector<E> with(int i, E e);

  FVector<E> plus(int i, E e);

  FVector<E> plusAll(int i, Collection<? extends E> list);

  FVector<E> minus(Object e);

  FVector<E> minusAll(Collection<?> list);

  FVector<E> minus(int i);

  FVector<E> subList(int start, int end);

  FVector<E> filter(Predicate<? super E> predicate);

  <R> FVector<R> map(Function<? super E, ? extends R> mapFunction);

  E reduce(E initial, BinaryOperator<E> reduceOp);

  Optional<E> reduce(BinaryOperator<E> reduceOp);

  <A> A foldLeft(A initial, BiFunction<A, ? super E, A> foldOp);

  @Override
  @Deprecated
  default boolean removeIf(Predicate<? super E> predicate) {
    throw new UnsupportedOperationException();
  }

  @Override
  @Deprecated
  default void replaceAll(UnaryOperator<E> unaryOperator) {
    throw new UnsupportedOperationException();
  }

  @Override
  @Deprecated
  default void sort(Comparator<? super E> comparator) {
    throw new UnsupportedOperationException();
  }


}
