==========================================================
fcollections - functional, persistent collections for java
==========================================================

Overview
========

Fcollections is a functional, persistent collections library for java.
It's implemented as a functional wrapper around pcollections.

Motivation
==========

It's possible to write a single-statement in every decent programming language.

Python:

.. code:: python

    def qsort(l):
        return [] if not l else (
            qsort([n for n in l[1:] if n <= l[0]]) +
            [l[0]] +
            qsort([n for n in l[1:] if n > l[0]]))


Erlang:

.. code:: erlang

    qsort(L) ->
        case L of
            [] -> [];
            [H | T] -> qsort([N || N <- T, N =< H]) ++ [H]
                           ++ qsort([N || N <- T, N > H])
        end.


Java:

.. code:: java

    public static List<Integer> qsort(List<Integer> l) {
      return l.isEmpty()
          ? l
          : Stream.of(
              qsort(l.stream().skip(1).filter(n -> n <= l.get(0))
                  .collect(Collectors.toList())).stream(),
              Stream.of(l.get(0)),
              qsort(l.stream().skip(1).filter(n -> n > l.get(0))
                  .collect(Collectors.toList())).stream())
            .flatMap(x -> x)
            .collect(Collectors.toList());
    }

Scala:

.. code:: scala

    def qsort(l: Seq[Int]): Seq[Int] = l match {
      case Seq(h, t @ _ *) => qsort(t.filter(_ <= h)) ++
        Seq(h) ++ qsort(t.filter(_ > h))
      case _ => l
    }

F#:

.. code:: fsharp

    let rec qsort l =
      match l with
      | h :: t -> (qsort(List.filter (fun n -> n <= h) t) @
                   [h] @ qsort(List.filter (fun n -> n > h) t))
      | [] -> []


======
Status
======

Good jokes mate real funny see u at JAX INNOVATION AWARDSJ.

Seriously though, this is a joke. FVector in this library has only enough methods for
a quicksort implementation.
