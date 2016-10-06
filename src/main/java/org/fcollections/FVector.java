package org.fcollections;

import org.pcollections.PVector;
import org.pcollections.TreePVector;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.*;

public abstract class FVector<E> extends AbstractList<E>
  implements PVector<E>, FCollection<E>, FCollectionOps<E, FVector<E>> {

  public static <E> FVector<E> empty() {
    return new TreeFVector<>(TreePVector.empty());
  }

  public static <E> FVector<E> of(E... args) {
    FVector<E> result = FVector.empty();
    for (E arg : args) {
      result = result.plus(arg);
    }
    return result;
  }

  @SuppressWarnings("unchecked")
  public static <E> FVector<E> concat(FVector<? extends E> arg1,
                                      FVector<? extends E> arg2) {
    return ((FVector<E>) arg1).plusAll(arg2);
  }

  @SuppressWarnings("unchecked")
  public static <E> FVector<E> concat(FVector<? extends E> arg1,
                                      FVector<? extends E> arg2,
                                      FVector<? extends E> arg3) {
    return ((FVector<E>) arg1).plusAll(arg2).plusAll(arg3);
  }

  @SuppressWarnings("unchecked")
  public static <E> FVector<E> concat(FVector<? extends E>... args) {
    FVector<E> result = ((FVector<E>) args[0]);
    for (int i = 1; i < args.length; i++) {
      result = result.plusAll(args[i]);
    }
    return result;
  }

  @Override public abstract FVector<E> tail();

  @Override public abstract FVector<E> plus(E e);

  @Override public abstract FVector<E> plusAll(Collection<? extends E> list);

  @Override public abstract FVector<E> with(int i, E e);

  @Override public abstract FVector<E> plus(int i, E e);

  @Override public abstract FVector<E> plusAll(int i, Collection<? extends E> list);

  @Override public abstract FVector<E> minus(Object e);

  @Override public abstract FVector<E> minusAll(Collection<?> list);

  @Override public abstract FVector<E> minus(int i);

  @Override public abstract FVector<E> subList(int start, int end);

  @Override public abstract <R> FVector<R> map(Function<? super E, ? extends R> mapFunction);

  @Override
  @Deprecated
  public void replaceAll(UnaryOperator<E> unaryOperator) {
    throw new UnsupportedOperationException();
  }

  @Override
  @Deprecated
  public void sort(Comparator<? super E> comparator) {
    throw new UnsupportedOperationException();
  }
}
