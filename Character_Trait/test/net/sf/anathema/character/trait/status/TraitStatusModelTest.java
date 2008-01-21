package net.sf.anathema.character.trait.status;

import net.disy.commons.core.model.listener.IChangeListener;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class TraitStatusModelTest {

  private TraitStatusModel statusModel;

  @Before
  public void createModel() {
    statusModel = new TraitStatusModel();
  }

  @Test
  public void throwsEventOnStatusChange() throws Exception {
    IChangeListener listener = EasyMock.createMock(IChangeListener.class);
    listener.stateChanged();
    EasyMock.replay(listener);
    statusModel.addChangeListener(listener );
    statusModel.setStatus(new FavoredStatus());
    EasyMock.verify(listener);
  }
}