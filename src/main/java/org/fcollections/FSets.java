package org.fcollections;

import org.pcollections.HashTreePMap;
import org.pcollections.MapPSet;
import org.pcollections.PSet;

public class FSets {
  public static <E> FSet<E> empty() {
    return new MapFSet<>(MapPSet.from(HashTreePMap.empty()));
  }

  public static <E> FSet<E> of(E... args) {
    FSet<E> result = FSets.empty();
    for (E arg : args) {
      result = result.plus(arg);
    }
    return result;
  }
}
