package net.sf.anathema.character.trait.status;

import static org.junit.Assert.*;
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
  
  @Test
  public void hasDefaultStatus() throws Exception {
    assertTrue(statusModel.getStatus() instanceof DefaultStatus);
  }
  
  @Test
  public void togglesFromDefaultToFavoredStatus() throws Exception {
    statusModel.setStatus(new DefaultStatus());
    statusModel.toggleStatus();
    assertTrue(statusModel.getStatus() instanceof FavoredStatus);
  }
  
  @Test
  public void togglesFromFavoredToDefaultStatus() throws Exception {
    statusModel.setStatus(new FavoredStatus());
    statusModel.toggleStatus();
    assertTrue(statusModel.getStatus() instanceof DefaultStatus);
  }
  
  @Test
  public void doesNotToggleFromUnmodifiableStatus() throws Exception {
    ITraitStatus status = EasyMock.createMock(ITraitStatus.class);
    EasyMock.expect(status.isModifiable()).andReturn(false).anyTimes();
    EasyMock.replay(status);
    statusModel.setStatus(status);
    statusModel.toggleStatus();
    assertSame(status, statusModel.getStatus());
  }
}