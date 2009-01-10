package net.sf.anathema.test.hamcrest;

import java.util.Collection;

import org.hamcrest.Description;
import org.junit.matchers.TypeSafeMatcher;

public class IsEmpty extends TypeSafeMatcher<Collection< ? >> {

  @Override
  public void describeTo(Description description) {
    description.appendText("an empty collection"); //$NON-NLS-1$
  }

  @Override
  public boolean matchesSafely(Collection< ? > collection) {
    return collection.isEmpty();
  }
}