package org.fcollections;

import org.pcollections.TreePVector;

public class FVectors {
  public static <E> FVector<E> empty() {
    return new TreeFVector<>(TreePVector.empty());
  }

  public static <E> FVector<E> of(E... args) {
    FVector<E> result = FVectors.empty();
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
}
