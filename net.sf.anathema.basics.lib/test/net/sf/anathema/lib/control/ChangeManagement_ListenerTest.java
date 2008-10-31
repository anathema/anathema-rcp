package net.sf.anathema.lib.control;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.disy.commons.core.creation.IFactory;
import net.disy.commons.core.model.listener.IChangeListener;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ChangeManagement_ListenerTest {

  private static final class LazyChangeListener implements IChangeListener {
    @Override
    public void stateChanged() {
      // nothing to do
    }
  }

  private static AggregatedChangeManagement createAggregatedChangeManagement() {
    AggregatedChangeManagement aggregatedManagement = new AggregatedChangeManagement();
    IChangeManagement filledChangeManagement = new ChangeManagement();
    filledChangeManagement.addDirtyListener(EasyMock.createMock(IChangeListener.class));
    aggregatedManagement.setChangeManagments(filledChangeManagement, new ChangeManagement());
    return aggregatedManagement;
  }

  @Parameters
  public static Collection<Object[]> data() {
    List<Object[]> data = new ArrayList<Object[]>();
    data.add(new Object[] { new IFactory<IChangeManagement, RuntimeException>() {
      @Override
      public IChangeManagement createInstance() throws RuntimeException {
        return new ChangeManagement();
      }
    }});
    data.add(new Object[] { new IFactory<IChangeManagement, RuntimeException>() {
      @Override
      public IChangeManagement createInstance() throws RuntimeException {
        return createAggregatedChangeManagement();
      }
    }});
    return data;
  }

  private IChangeManagement changeManagement;
  private final IFactory<IChangeManagement, RuntimeException> factory;

  public ChangeManagement_ListenerTest(IFactory<IChangeManagement, RuntimeException> factory) {
    this.factory = factory;
  }
  
  @Before
  public void createChangeManagement() {
    this.changeManagement = factory.createInstance();
  }

  @Test
  public void noListenersRegistered() throws Exception {
    assertEquals(0, changeManagement.getDirtyListenerCount());
  }

  @Test
  public void listenerCountIncreasesWithAddition() throws Exception {
    changeManagement.addDirtyListener(EasyMock.createMock(IChangeListener.class));
    assertEquals(1, changeManagement.getDirtyListenerCount());
  }

  @Test
  public void listenerCountDecreasesWithRemoval() throws Exception {
    IChangeListener listener = new LazyChangeListener();
    changeManagement.addDirtyListener(listener);
    assertEquals(1, changeManagement.getDirtyListenerCount());
    changeManagement.removeDirtyListener(listener);
    assertEquals(0, changeManagement.getDirtyListenerCount());
  }
}