package net.sf.anathema.test.hamcrest;

import org.hamcrest.Matcher;

public class AnathemaMatchers {

  public static <T> Matcher<T[]> contains(T value) {
    return new Contains<T>(value);
  }

  public static Matcher<Iterable< ? >> empty() {
    return new IsEmpty();
  }
}