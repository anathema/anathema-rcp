package net.sf.anathema.character.trait;

import static org.junit.Assert.*;
import net.sf.anathema.lib.control.change.IChangeListener;

import org.junit.Before;
import org.junit.Test;

public class IntValueModelTest {

  private IntValueModel model;

  @Before
  public void createModel() {
    this.model = new IntValueModel();
  }

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
    model.addChangeListener(new IChangeListener() {
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
    model.addChangeListener(new IChangeListener() {
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
    model.addChangeListener(changeListener);
    model.removeChangeListener(changeListener);
    model.setValue(1);
    assertFalse(eventReceived[0]);
  }
}