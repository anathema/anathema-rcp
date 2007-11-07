package net.sf.anathema.basics.repository.fake;

import org.easymock.EasyMock;
import org.eclipse.ui.IMemento;

public class MementoObjectMother {

  public static IMemento createStringReturningMemento() {
    IMemento memento = EasyMock.createNiceMock(IMemento.class);
    EasyMock.expect(memento.getString(EasyMock.isA(String.class))).andReturn("karzenfuﬂ"); //$NON-NLS-1$
    EasyMock.replay(memento);
    return memento;
  }
}