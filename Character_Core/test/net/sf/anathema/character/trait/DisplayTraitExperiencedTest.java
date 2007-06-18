package net.sf.anathema.character.trait;

import static org.junit.Assert.*;
import net.sf.anathema.character.basics.ICharacterBasics;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class DisplayTraitExperiencedTest extends AbstractIntValueModelTest {
  private boolean experienced = false;

  @Before
  public void createTrait() {
    ICharacterBasics basics = new ICharacterBasics() {
      @Override
      public boolean isExperienced() {
        return experienced;
      }
    };
    this.model = new DisplayTrait(new BasicTrait(new Identificate("test")), basics); //$NON-NLS-1$
    experienced = true;
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