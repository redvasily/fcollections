package org.fcollections;

import org.pcollections.Empty;
import org.pcollections.HashTreePMap;
import org.pcollections.MapPSet;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public final class MapFSet<E> extends AbstractSet<E> implements FSet<E> {

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
  public MapFSet<E> filter(Predicate<? super E> predicate) {
    MapFSet<E> result = this;
    for (E e : this) {
      if (!predicate.test(e)) {
        result = result.minus(e);
      }
    }
    return result;
  }

  @Override
  public <R> MapFSet<R> map(Function<? super E, ? extends R> mapFunction) {
    MapFSet<R> result = new MapFSet<>(MapPSet.from(HashTreePMap.empty()));
    for (E e : this) {
      result = result.plus(mapFunction.apply(e));
    }
    return result;
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


}
