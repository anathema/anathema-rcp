package net.sf.anathema.test.hamcrest;

import java.util.Collection;

import org.hamcrest.Matcher;

public class AnathemaMatchers {

  public static <T> Matcher<T[]> contains(T value) {
    return new Contains<T>(value);
  }
  
  public static  Matcher<Collection<?>> empty() {
    return new IsEmpty();
  }
}