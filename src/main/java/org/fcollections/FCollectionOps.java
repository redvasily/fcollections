package org.fcollections;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

public interface FCollectionOps<E, CE> extends Collection<E> {
  Builder<E, CE> newBuilder();

  default CE filter(Predicate<? super E> predicate) {
    Builder<E, CE> builder = newBuilder();

    for (E element : this) {
      if (predicate.test(element)) {
        builder.add(element);
      }
    }

    return builder.result();
  }
}
