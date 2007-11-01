package net.sf.anathema.character.trait.interactive;

import static org.junit.Assert.*;
import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.DummyTraitTemplate;
import net.sf.anathema.character.trait.fake.DummyTraitPreferences;
import net.sf.anathema.character.trait.preference.ExperienceTraitTreatment;
import net.sf.anathema.character.trait.preference.ITraitPreferences;
import net.sf.anathema.lib.util.Identificate;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InteractiveTraitExperiencedTest {

  private DummyTraitTemplate traitTemplate;
  private Identificate traitType;
  private BasicTrait basicTrait;
  private IInteractiveFavorization favorization;
  private InteractiveTrait interactiveTrait;

  @Before
  public final void createTrait() {
    IExperience experienced = new DummyExperience();
    experienced.setExperienced(true);
    this.traitTemplate = new DummyTraitTemplate();
    this.traitType = new Identificate("test"); //$NON-NLS-1$
    this.basicTrait = new BasicTrait(traitType);
    this.favorization = EasyMock.createMock(IInteractiveFavorization.class);
    ITraitPreferences traitPreferences = new DummyTraitPreferences(ExperienceTraitTreatment.LeaveUnchanged);
    this.interactiveTrait = new InteractiveTrait(basicTrait, experienced, favorization, traitTemplate, traitPreferences);
  }

  @Test
  public void alertsListenersIfExperiencedValueIsFirstSetToCurrentValue() throws Exception {
    final boolean[] eventReceived = new boolean[] { false };
    interactiveTrait.addChangeListener(new IChangeListener() {
      @Override
      public void stateChanged() {
        eventReceived[0] = true;
      }
    });
    interactiveTrait.setValue(0);
    assertTrue(eventReceived[0]);
  }

  @Test
  public void favorizationIsDisposedOnDispose() throws Exception {
    favorization.dispose();
    EasyMock.replay(favorization);
    getDisplayTrait().dispose();
    EasyMock.verify(favorization);
  }

  protected final InteractiveTrait getDisplayTrait() {
    return interactiveTrait;
  }

  @Test
  public void hasBasicTraitType() throws Exception {
    Assert.assertEquals(traitType, getDisplayTrait().getTraitType());
  }

  @Test
  public void hasInitialValue0() throws Exception {
    assertEquals(0, interactiveTrait.getValue());
  }

  @Test
  public void isSilentIfValueRemains() throws Exception {
    interactiveTrait.setValue(0);
    final boolean[] eventReceived = new boolean[] { false };
    interactiveTrait.addChangeListener(new IChangeListener() {
      @Override
      public void stateChanged() {
        eventReceived[0] = true;
      }
    });
    interactiveTrait.setValue(0);
    assertFalse(eventReceived[0]);
  }

  @Test
  public void notifiesListenersIfValueChanges() throws Exception {
    final boolean[] eventReceived = new boolean[] { false };
    interactiveTrait.addChangeListener(new IChangeListener() {
      @Override
      public void stateChanged() {
        eventReceived[0] = true;
      }
    });
    interactiveTrait.setValue(1);
    assertTrue(eventReceived[0]);
  }

  @Test
  public void removesListeners() throws Exception {
    final boolean[] eventReceived = new boolean[] { false };
    IChangeListener changeListener = new IChangeListener() {
      @Override
      public void stateChanged() {
        eventReceived[0] = true;
      }
    };
    interactiveTrait.addChangeListener(changeListener);
    interactiveTrait.removeChangeListener(changeListener);
    interactiveTrait.setValue(1);
    assertFalse(eventReceived[0]);
  }

  @Test
  public void removesValueListenersWhenDisposedOf() throws Exception {
    final boolean[] eventReceived = new boolean[] { false };
    getDisplayTrait().addChangeListener(new IChangeListener() {
      @Override
      public void stateChanged() {
        eventReceived[0] = true;
      }
    });
    getDisplayTrait().dispose();
    assertEquals(0, basicTrait.getListenerCount());
  }

  @Test
  public void respectsMaximalValueFromRules() throws Exception {
    this.traitTemplate.setMaximalValue(6);
    assertEquals(6, getDisplayTrait().getMaximalValue());
  }

  @Test
  public void storesValue() throws Exception {
    interactiveTrait.setValue(1);
    assertEquals(1, interactiveTrait.getValue());
  }
}