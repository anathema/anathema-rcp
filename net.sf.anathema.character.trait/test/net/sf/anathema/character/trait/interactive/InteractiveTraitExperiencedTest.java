package net.sf.anathema.character.trait.interactive;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.experience.Experience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.fake.DummyTraitPreferences;
import net.sf.anathema.character.trait.preference.ExperienceTraitTreatment;
import net.sf.anathema.character.trait.preference.ITraitPreferences;
import net.sf.anathema.character.trait.validator.IValidator;
import net.sf.anathema.lib.util.Identificate;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InteractiveTraitExperiencedTest {

  private Identificate traitType;
  private BasicTrait basicTrait;
  private IInteractiveFavorization favorization;
  private InteractiveTrait interactiveTrait;

  @Before
  public final void createTrait() {
    IExperience experience = new Experience();
    experience.setExperienced(true);
    traitType = new Identificate("test"); //$NON-NLS-1$
    basicTrait = new BasicTrait(traitType);
    favorization = EasyMock.createNiceMock(IInteractiveFavorization.class);
    ITraitPreferences traitPreferences = new DummyTraitPreferences(ExperienceTraitTreatment.LeaveUnchanged);
    interactiveTrait = new InteractiveTrait(
        basicTrait,
        experience,
        favorization,
        new ArrayList<IValidator>(),
        traitPreferences,
        5);
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
    reset(favorization);
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
    replay(favorization);
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
  public void storesValue() throws Exception {
    interactiveTrait.setValue(1);
    assertEquals(1, interactiveTrait.getValue());
  }
}