package net.sf.anathema.test.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class IsEmpty extends TypeSafeMatcher<Iterable< ? >> {

  @Override
  public void describeTo(Description description) {
    description.appendText("an empty collection"); //$NON-NLS-1$
  }

  @Override
  public boolean matchesSafely(Iterable< ? > collection) {
    return !collection.iterator().hasNext();
  }
}