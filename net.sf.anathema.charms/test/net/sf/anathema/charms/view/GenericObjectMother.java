package net.sf.anathema.charms.view;

import static org.easymock.EasyMock.*;

public class GenericObjectMother {

  public static <T> T createDummy(Class<T> clazz) {
    T mock = createNiceMock(clazz);
    replay(mock);
    return mock;
  }
}