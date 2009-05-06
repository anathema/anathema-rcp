package net.sf.anathema.test.hamcrest;

import net.disy.commons.core.util.ArrayUtilities;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class Contains<T> extends TypeSafeMatcher<T[]> {

  private final T expectation;

  public Contains(T expectation) {
    this.expectation = expectation;
  }

  @Override
  public boolean matchesSafely(T[] actual) {
    return ArrayUtilities.containsValue(actual, expectation);
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("an array containing "); //$NON-NLS-1$
    description.appendValue(expectation);
  }
}