package net.sf.anathema.lib.ui;

import net.disy.commons.core.model.IChangeableModel;
import net.disy.commons.core.model.listener.IChangeListener;

import org.easymock.EasyMock;
import org.junit.Test;

public class ChangableModelDisposableTest {

  @Test
  public void listenerIsRemovedOnDispose() throws Exception {
    IChangeListener listener = EasyMock.createMock(IChangeListener.class);
    IChangeableModel model = EasyMock.createMock(IChangeableModel.class);
    model.removeChangeListener(listener);
    ChangeableModelDisposable disposable = new ChangeableModelDisposable(model, listener);
    EasyMock.replay(model);
    disposable.dispose();
    EasyMock.verify(model);
  }
}