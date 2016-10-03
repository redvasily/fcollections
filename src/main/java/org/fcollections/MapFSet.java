package org.fcollections;

import org.pcollections.MapPSet;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public final class MapFSet<E> extends FSet<E> {

  private static class MapFSetBuilder<E> implements Builder<E, FSet<E>> {

    private FSet<E> acc;

    private MapFSetBuilder() {
      acc = FSet.empty();
    }

    @Override
    public void add(E element) {
      acc = acc.plus(element);
    }

    @Override
    public FSet<E> result() {
      return acc;
    }
  }


  private MapPSet<E> pset;

  MapFSet(MapPSet<E> pset) { this.pset = pset; }

  @Override
  public Iterator<E> iterator() { return pset.iterator(); }

  @Override
  public int size() { return pset.size(); }

  @Override
  public MapFSet<E> plus(E e) {
    return new MapFSet<>(pset.plus(e));
  }

  @Override
  public MapFSet<E> plusAll(Collection<? extends E> list) {
    return new MapFSet<>(pset.plusAll(list));
  }

  @Override
  public MapFSet<E> minus(Object e) {
    return new MapFSet<>(pset.minus(e));
  }

  @Override
  public MapFSet<E> minusAll(Collection<?> list) {
    return new MapFSet<>(pset.minusAll(list));
  }

  @Override
  public E head() {
    Iterator<E> it = pset.iterator();
    if (!it.hasNext()) {
      throw new IllegalStateException();
    }
    return it.next();
  }

  @Override
  public MapFSet<E> tail() {
    return minus(head());
  }

  @Override
  public Builder<E, FSet<E>> newBuilder() {
    return new MapFSetBuilder<>();
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
    E acc = head();
    for (E e : tail()) {
      acc = reduceOp.apply(acc, e);
    }
    return Optional.of(acc);
  }

  @Override
  public <R> FSet<R> map(Function<? super E, ? extends R> mapFunction) {
    FSet<R> result = FSet.empty();
    for (E e : this) {
      result = result.plus(mapFunction.apply(e));
    }
    return result;
  }
}
