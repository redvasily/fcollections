package org.fcollections;

import org.pcollections.TreePVector;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class TreeFVector<E> extends FVector<E> {

  private TreePVector<E> pvector;

  private static class TreeFVectorBuilder<E> implements Builder<E, FVector<E>> {

    private FVector<E> acc;

    private TreeFVectorBuilder() {
      acc = FVector.empty();
    }

    @Override
    public void add(E element) {
      acc = acc.plus(element);
    }

    @Override
    public FVector<E> result() {
      return acc;
    }
  }

  @Override
  public Builder<E, FVector<E>> newBuilder() {
    return new TreeFVectorBuilder<>();
  }

  TreeFVector(TreePVector<E> pvector) {
    this.pvector = pvector;
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
  public TreeFVector<E> subList(int start, int end) {
    return new TreeFVector<>(pvector.subList(start, end));
  }

  @Override
  public TreeFVector<E> plus(E e) {
    return new TreeFVector<>(pvector.plus(e));
  }

  @Override
  public TreeFVector<E> plus(int i, E e) {
    return new TreeFVector<>(pvector.plus(i, e));
  }

  @Override
  public TreeFVector<E> minus(Object e) {
    return new TreeFVector<>(pvector.minus(e));
  }

  @Override
  public TreeFVector<E> minus(final int i) {
    return new TreeFVector<>(pvector.minus(i));
  }

  @Override
  public TreeFVector<E> plusAll(Collection<? extends E> list) {
    return new TreeFVector<>(pvector.plusAll(list));
  }

  @Override
  public TreeFVector<E> minusAll(Collection<?> list) {
    return new TreeFVector<>(pvector.minusAll(list));
  }

  @Override
  public TreeFVector<E> plusAll(int i, Collection<? extends E> list) {
    return new TreeFVector<>(pvector.plusAll(i, list));
  }

  @Override
  public TreeFVector<E> with(int i, E e) {
    return new TreeFVector<>((TreePVector<E>) pvector.with(i, e));
  }

  @Override
  public E head() {
    return this.pvector.get(0);
  }

  @Override
  public FVector<E> tail() {
    return this.minus(0);
  }

  @Override
  public E reduce(E initial, BinaryOperator<E> reduceOp) {
    E acc = initial;
    for (E e : this) {
      acc = reduceOp.apply(acc, e);
    }
    return acc;
  }

  @Override
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

  @Override
  public <A> A foldLeft(A initial, BiFunction<A, ? super E, A> foldOp) {
    A acc = initial;
    for (E e : this) {
      acc = foldOp.apply(acc, e);
    }
    return acc;
  }

  @Override
  public <R> FVector<R> map(Function<? super E, ? extends R> mapFunction) {
    FVector<R> result = FVector.empty();
    for (E e : this) {
      result = result.plus(mapFunction.apply(e));
    }
    return result;
  }
}
