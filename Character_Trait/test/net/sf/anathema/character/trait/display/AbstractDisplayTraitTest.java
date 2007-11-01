package net.sf.anathema.character.trait.display;

import static org.junit.Assert.*;
import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.AbstractIntValueModelTest;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.DisplayTrait;
import net.sf.anathema.character.trait.DummyTraitTemplate;
import net.sf.anathema.character.trait.IDisplayFavorization;
import net.sf.anathema.character.trait.preference.ExperienceTraitTreatment;
import net.sf.anathema.character.trait.preference.ITraitPreferences;
import net.sf.anathema.lib.util.Identificate;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractDisplayTraitTest extends AbstractIntValueModelTest {

  private DummyTraitTemplate traitTemplate;
  private Identificate traitType;
  private BasicTrait basicTrait;
  private IDisplayFavorization favorization;

  protected abstract IExperience createExperience();

  @Before
  public final void createTrait() {
    IExperience basics = createExperience();
    this.traitTemplate = new DummyTraitTemplate();
    this.traitType = new Identificate("test"); //$NON-NLS-1$
    this.basicTrait = new BasicTrait(traitType);
    this.favorization = EasyMock.createMock(IDisplayFavorization.class);
    ITraitPreferences traitPreferences = new DummyTraitPreferences(ExperienceTraitTreatment.LeaveUnchanged);
    this.model = new DisplayTrait(basicTrait, basics, favorization, traitTemplate, traitPreferences);
  }

  protected final DisplayTrait getDisplayTrait() {
    return (DisplayTrait) model;
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
}