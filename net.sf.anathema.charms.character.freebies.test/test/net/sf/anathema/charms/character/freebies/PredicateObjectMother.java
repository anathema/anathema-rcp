package net.sf.anathema.charms.character.freebies;

import static org.easymock.EasyMock.*;
import net.disy.commons.core.predicate.IPredicate;

public class PredicateObjectMother {

  @SuppressWarnings("unchecked")
  public static <T> IPredicate<T> create(T... trueValues) {
    IPredicate<T> predicate = createNiceMock(IPredicate.class);
    for (T trueValue : trueValues) {
      expect(predicate.evaluate(trueValue)).andStubReturn(true);
    }
    replay(predicate);
    return predicate;
  }
}