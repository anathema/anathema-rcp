package net.sf.anathema.character.trait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.experience.DummyExperience;
import net.sf.anathema.lib.control.change.IChangeListener;

import org.junit.Test;

public class DisplayTraitExperiencedTest extends AbstractDisplayTraitTest {
  private boolean experienced = true;

  @Override
  protected IExperience createExperience() {
    return new DummyExperience() {
      @Override
      public boolean isExperienced() {
        return experienced;
      }
    };
  }

  @Test
  public void doesNotChangeCreationValueIfExperiencedValueChanges() throws Exception {
    model.setValue(5);
    experienced = false;
    assertEquals(0, model.getValue());
  }

  @Override
  @Test
  public void isSilentIfValueRemains() throws Exception {
    model.setValue(0);
    super.isSilentIfValueRemains();
  }
  
  @Test
  public void alertsListenersIfExperiencedValueIsFirstSetToCurrentValue() throws Exception {
    final boolean[] eventReceived = new boolean[] { false };
    model.addValueChangeListener(new IChangeListener() {
      @Override
      public void changeOccured() {
        eventReceived[0] = true;
      }
    });
    model.setValue(0);
    assertTrue(eventReceived[0]);
  }
}