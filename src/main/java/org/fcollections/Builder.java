package org.fcollections;

public interface Builder<E, C> {
  void add(E element);
  C result();
}
