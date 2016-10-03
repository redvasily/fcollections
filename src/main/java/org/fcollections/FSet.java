package org.fcollections;

import org.pcollections.HashTreePMap;
import org.pcollections.MapPSet;
import org.pcollections.PSet;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class FSet<E> extends AbstractSet<E>
  implements PSet<E>, FCollection<E, FSet<E>> {

  public static <E> FSet<E> empty() {
    return new MapFSet<>(MapPSet.from(HashTreePMap.empty()));
  }

  public static <E> FSet<E> of(E... args) {
    FSet<E> result = FSet.empty();
    for (E arg : args) {
      result = result.plus(arg);
    }
    return result;
  }

  @Override public abstract FSet<E> plus(E e);
  @Override public abstract FSet<E> plusAll(Collection<? extends E> list);
  @Override public abstract FSet<E> minus(Object e);
  @Override public abstract FSet<E> minusAll(Collection<?> list);

  public abstract E head();
  public abstract FSet<E> tail();
  public abstract E reduce(E initial, BinaryOperator<E> reduceOp);
  public abstract Optional<E> reduce(BinaryOperator<E> reduceOp);

  public abstract <R> FSet<R> map(Function<? super E, ? extends R> mapFunction);

  @Override
  @Deprecated
  public boolean removeIf(Predicate<? super E> predicate) {
    throw new UnsupportedOperationException();
  }
}
