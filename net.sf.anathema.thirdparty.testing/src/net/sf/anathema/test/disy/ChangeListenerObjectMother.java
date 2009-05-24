package net.sf.anathema.test.disy;

import static org.easymock.EasyMock.*;
import net.disy.commons.core.model.listener.IChangeListener;

public class ChangeListenerObjectMother {

  public static IChangeListener CreateNotifiedChangeListener() {
    IChangeListener listener = createMock(IChangeListener.class);
    listener.stateChanged();
    replay(listener);
    return listener;
  }

  public static IChangeListener CreateUnnotifiedChangeListener() {
    IChangeListener listener = createMock(IChangeListener.class);
    replay(listener);
    return listener;
  }
}