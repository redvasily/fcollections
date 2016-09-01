package org.fcollections;

public class App {

  public static FVector<Integer> qsort(FVector<Integer> data) {
    return data.isEmpty() ? data : FVectors.concat(
        qsort(data.tail().filter(x -> x <= data.head())),
        FVectors.of(data.head()),
        qsort(data.tail().filter(x -> x > data.head())));
  }

  public static void main(String[] args) {
    FVector<Integer> fv = FVectors.empty();
    fv = fv.plus(1);
    fv = fv.plus(5);
    fv = fv.plus(10);
    System.out.println(fv);
    fv = fv.with(0, 20);
    System.out.println(fv);

    fv = fv.filter(n -> n % 2 == 0);
    System.out.println(fv);
    FVector<Integer> neg = fv.map(x -> -x);
    System.out.println(neg);

    {
      FVector<Integer> v = FVectors.of(1, 2, 3, 4);
      System.out.println(v);
    }

    {
      FVector<Integer> v = FVectors.concat(
          FVectors.of(1, 2, 3),
          FVectors.of(4, 5, 6));
      System.out.println(v);
    }

    {
      FVector<Integer> v = qsort(FVectors.of(10, 20, 1, 8, 2, 6, -5));
      System.out.println(v);
    }

    {
      FVector<Integer> v = FVectors.of(1, 2, 3, 4, 5);
      System.out.println(v.reduce((a, b) -> a + b));
      System.out.println(v.reduce(0, (a, b) -> a + b));
    }

    {
      FVector<Integer> v = FVectors.of(1, 2, 3, 4, 5, 6, 8);
      Integer r = v.foldLeft(0, (acc, e) -> e % 2 == 0 ? acc + 1 : acc);
      System.out.println(r);
    }

  }
}
