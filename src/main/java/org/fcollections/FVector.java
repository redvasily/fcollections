package org.fcollections;

import org.pcollections.PVector;
import org.pcollections.TreePVector;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public class FVector<E> extends AbstractList<E> implements PVector<E> {

  private TreePVector<E> pvector;

  private FVector(TreePVector<E> pvector) {
    this.pvector = pvector;
  }

  public static <E> FVector<E> empty() {
    return new FVector<>(TreePVector.empty());
  }

  public static <E> FVector<E> of(E... args) {
    FVector<E> result = empty();
    for (E arg : args) {
      result = result.plus(arg);
    }
    return result;
  }

  public static <E> FVector<E> copyOf(Collection<? extends E> list) {
    if (list instanceof TreePVector) {
      return new FVector<>((TreePVector<E>) list);
    } else if (list instanceof FVector) {
      return (FVector<E>) list;
    } else {
      return FVector.<E>empty().plusAll(list);
    }
  }

//    public static <E> FVector<E> concat()

  public static <E> FVector<E> concat(FVector<? extends E> arg1,
                                      FVector<? extends E> arg2) {
    FVector<E> result = empty();
    result = result.plusAll(arg1);
    result = result.plusAll(arg2);
    return result;
  }

  public static <E> FVector<E> concat(FVector<? extends E> arg1,
                                      FVector<? extends E> arg2,
                                      FVector<? extends E> arg3) {
    FVector<E> result = empty();
    result = result.plusAll(arg1);
    result = result.plusAll(arg2);
    result = result.plusAll(arg3);
    return result;
  }

  @Override
  public int size() {
    return pvector.size();
  }

  @Override
  public E get(int i) {
    return pvector.get(i);
  }

  @Override
  public Iterator<E> iterator() {
    return pvector.iterator();
  }

  @Override
  public FVector<E> subList(int start, int end) {
    return new FVector<>(pvector.subList(start, end));
  }

  @Override
  public FVector<E> plus(E e) {
    return new FVector<>(pvector.plus(e));
  }

  @Override
  public FVector<E> plus(int i, E e) {
    return new FVector<>(pvector.plus(i, e));
  }

  @Override
  public FVector<E> minus(Object e) {
    return new FVector<>(pvector.minus(e));
  }

  @Override
  public FVector<E> minus(final int i) {
    return new FVector<>(pvector.minus(i));
  }

  @Override
  public FVector<E> plusAll(Collection<? extends E> list) {
    return new FVector<>(pvector.plusAll(list));
  }

  @Override
  public FVector<E> minusAll(Collection<?> list) {
    return new FVector<>(pvector.minusAll(list));
  }

  @Override
  public FVector<E> plusAll(int i, Collection<? extends E> list) {
    return new FVector<>(pvector.plusAll(i, list));
  }

  @Override
  public FVector<E> with(int i, E e) {
    return new FVector<>((TreePVector<E>) pvector.with(i, e));
  }

  // actual functional stuff
  public FVector<E> filter(Predicate<? super E> predicate) {
    FVector<E> result = this;
    int i = 0;
    for (E e : this) {
      if (predicate.test(e)) {
        i += 1;
      } else {
        result = result.minus(i);
      }
    }
    return result;
  }

  public <R> FVector<R> map(Function<? super E, ? extends R> mapFunction) {
    FVector<R> result = FVector.empty();
    for (E e : this) {
      result = result.plus(mapFunction.apply(e));
    }
    return result;
  }

  public E head() {
    return this.pvector.get(0);
  }

  public FVector<E> tail() {
    return this.minus(0);
  }

  public E reduce(E initial, BinaryOperator<E> reduceOp) {
    E acc = initial;
    for (E e : this) {
      acc = reduceOp.apply(acc, e);
    }
    return acc;
  }

  public Optional<E> reduce(BinaryOperator<E> reduceOp) {
    if (isEmpty()) {
      return Optional.empty();
    }
    E acc = get(0);
    for (E e : tail()) {
      acc = reduceOp.apply(acc, e);
    }
    return Optional.of(acc);
  }

  public <A> A foldLeft(A initial, BiFunction<A, ? super E, A> foldOp) {
    A acc = initial;
    for (E e : this) {
      acc = foldOp.apply(acc, e);
    }
    return acc;
  }

  @Override
  @Deprecated
  public boolean add(E e) {
    throw new UnsupportedOperationException();
  }

  @Override
  @Deprecated
  public boolean addAll(int i, Collection<? extends E> collection) {
    throw new UnsupportedOperationException();
  }

  @Override
  @Deprecated
  public E set(int i, E e) {
    throw new UnsupportedOperationException();
  }

  @Override
  @Deprecated
  public E remove(int i) {
    throw new UnsupportedOperationException();
  }

  @Override
  @Deprecated
  protected void removeRange(int i, int i1) {
    throw new UnsupportedOperationException();
  }
}
