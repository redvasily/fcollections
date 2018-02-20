==========================================================
fcollections - functional, persistent collections for java
==========================================================

Overview
========

Fcollections is a functional, persistent collections library for java.
It's implemented as a functional wrapper around pcollections_.

Motivation
==========

It's possible to write a single-statement quicksort in every decent programming language.

Python:

.. code:: python

    def qsort(l):
        return [] if not l else (
            qsort([n for n in l[1:] if n <= l[0]]) + [l[0]] + qsort([n for n in l[1:] if n > l[0]]))


Erlang:

.. code:: erlang

    qsort(L) ->
        case L of
            [] -> [];
            [H | T] -> qsort([N || N <- T, N =< H]) ++ [H] ++ qsort([N || N <- T, N > H])
        end.

Scala:

.. code:: scala

    def qsort(l: Seq[Int]): Seq[Int] = l match {
      case Seq(h, t @ _ *) => qsort(t.filter(_ <= h)) ++ Seq(h) ++ qsort(t.filter(_ > h))
      case _ => l
    }

F#:

.. code:: fsharp

    let rec qsort l =
      match l with
      | h :: t -> (qsort(List.filter (fun n -> n <= h) t) @ [h] @ qsort(List.filter (fun n -> n > h) t))
      | [] -> []


One would hope that with Java 8 lamba expressions and streams it would finally be possible to
write something like that in Java, and indeed it is. In a way:

.. code:: java

    public static List<Integer> qsort(List<Integer> l) {
      return l.isEmpty() ? l
          : Stream.of(
              qsort(l.stream().skip(1).filter(n -> n <= l.get(0)).collect(Collectors.toList())).stream(),
              Stream.of(l.get(0)),
              qsort(l.stream().skip(1).filter(n -> n > l.get(0)).collect(Collectors.toList())).stream())
            .flatMap(x -> x)
            .collect(Collectors.toList());
    }

Unfortunately standard Java collections are not "functional", and you have to call ``.stream()``
first, and collect results back into a standard collection all the time if you try to write
code in the functional style. The fact that Java collections are not persistent also means that
there's significant overhead to creating new collections all the time.

There is a decent persistent collections library for java: pcollections_. Pcollections seem to
be really solid and has been recently "endorsed" by Lightbend_ in their Lagom_ framework.
However, pcollections doesn't provide functional interfaces such as filter, map, etc.

Enter fcollections - a functional wrapper around pcollections_.

Fcollections make the following code possible:


.. code:: java

    public static FVector<Integer> qsort(FVector<Integer> data) {
      return data.isEmpty() ? data : FVector.concat(
          qsort(data.tail().filter(x -> x <= data.head())),
                FVector.of(data.head()),
                qsort(data.tail().filter(x -> x > data.head())));
    }


======
Status
======

Extended FVector to support all methods needed for a quicksort implementation.


.. _pcollections: http://pcollections.org/

.. _Lightbend: https://www.lightbend.com/

.. _Lagom: https://www.lightbend.com/lagom

