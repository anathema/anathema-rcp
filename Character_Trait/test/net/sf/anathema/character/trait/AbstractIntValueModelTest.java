package net.sf.anathema.character.trait;

import static org.junit.Assert.*;
import net.sf.anathema.lib.control.change.IChangeListener;

import org.junit.Test;

public abstract class AbstractIntValueModelTest {
  protected IIntValueModel model;

  @Test
  public void hasInitialValue0() throws Exception {
    assertEquals(0, model.getValue());
  }

  @Test
  public void storesValue() throws Exception {
    model.setValue(1);
    assertEquals(1, model.getValue());
  }

  @Test
  public void notifiesListenersIfValueChanges() throws Exception {
    final boolean[] eventReceived = new boolean[] { false };
    model.addValueChangeListener(new IChangeListener() {
      @Override
      public void changeOccured() {
        eventReceived[0] = true;
      }
    });
    model.setValue(1);
    assertTrue(eventReceived[0]);
  }

  @Test
  public void isSilentIfValueRemains() throws Exception {
    final boolean[] eventReceived = new boolean[] { false };
    model.addValueChangeListener(new IChangeListener() {
      @Override
      public void changeOccured() {
        eventReceived[0] = true;
      }
    });
    model.setValue(0);
    assertFalse(eventReceived[0]);
  }

  @Test
  public void removesListeners() throws Exception {
    final boolean[] eventReceived = new boolean[] { false };
    IChangeListener changeListener = new IChangeListener() {
      @Override
      public void changeOccured() {
        eventReceived[0] = true;
      }
    };
    model.addValueChangeListener(changeListener);
    model.removeValueChangeListener(changeListener);
    model.setValue(1);
    assertFalse(eventReceived[0]);
  }
}
