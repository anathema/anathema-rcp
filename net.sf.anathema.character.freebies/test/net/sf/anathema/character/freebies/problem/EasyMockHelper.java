package net.sf.anathema.character.freebies.problem;

import static org.easymock.EasyMock.*;

public class EasyMockHelper {

  public static <T> T createMockAndReplay(Class<T> toMock) {
    T mock = createMock(toMock);
    replay(mock);
    return mock;
  }

  public static <T> T createNiceMockAndReplay(Class<T> toMock) {
    T mock = createNiceMock(toMock);
    replay(mock);
    return mock;
  }
}