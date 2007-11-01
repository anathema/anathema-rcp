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

  @Before
  public final void createTrait() {
    IExperience basics = createExperience();
    this.traitTemplate = new DummyTraitTemplate();
    this.traitType = new Identificate("test"); //$NON-NLS-1$
    this.basicTrait = new BasicTrait(traitType);
    this.favorization = EasyMock.createMock(IInteractiveFavorization.class);
    ITraitPreferences traitPreferences = createTraitPreferences();
    this.model = new InteractiveTrait(basicTrait, basics, favorization, traitTemplate, traitPreferences);
  }

  protected ITraitPreferences createTraitPreferences() {
    return new DummyTraitPreferences(ExperienceTraitTreatment.LeaveUnchanged);
  }

  protected final InteractiveTrait getDisplayTrait() {
    return (InteractiveTrait) model;
  }

  @Test
  public void respectsMaximalValueFromRules() throws Exception {
    this.traitTemplate.setMaximalValue(6);
    assertEquals(6, getDisplayTrait().getMaximalValue());
  }

  @Test
  public void hasBasicTraitType() throws Exception {
    Assert.assertEquals(traitType, getDisplayTrait().getTraitType());
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
  public void favorizationIsDisposedOnDispose() throws Exception {
    favorization.dispose();
    EasyMock.replay(favorization);
    getDisplayTrait().dispose();
    EasyMock.verify(favorization);
  }

  private boolean experienced = true;

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

  @Test
  public void alertsListenersIfExperiencedValueIsFirstSetToCurrentValue() throws Exception {
    final boolean[] eventReceived = new boolean[] { false };
    model.addChangeListener(new IChangeListener() {
      @Override
      public void stateChanged() {
        eventReceived[0] = true;
      }
    });
    model.setValue(0);
    assertTrue(eventReceived[0]);
  }

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
    model.addChangeListener(new IChangeListener() {
      @Override
      public void stateChanged() {
        eventReceived[0] = true;
      }
    });
    model.setValue(1);
    assertTrue(eventReceived[0]);
  }

  @Test
  public void isSilentIfValueRemains() throws Exception {
    model.setValue(0);
    final boolean[] eventReceived = new boolean[] { false };
    model.addChangeListener(new IChangeListener() {
      @Override
      public void stateChanged() {
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
      public void stateChanged() {
        eventReceived[0] = true;
      }
    };
    model.addChangeListener(changeListener);
    model.removeChangeListener(changeListener);
    model.setValue(1);
    assertFalse(eventReceived[0]);
  }
}