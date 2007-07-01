package net.sf.anathema.character.trait;

import static org.junit.Assert.*;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractDisplayTraitTest extends AbstractIntValueModelTest {

  private DummyTraitRules traitRules;
  private Identificate traitType;
  private BasicTrait basicTrait;

  protected abstract IExperience createExperience();

  @Before
  public final void createTrait() {
    IExperience basics = createExperience();
    this.traitRules = new DummyTraitRules();
    this.traitType = new Identificate("test"); //$NON-NLS-1$
    this.basicTrait = new BasicTrait(traitType);
    this.model = new DisplayTrait(basicTrait, basics, traitRules);
  }

  protected final DisplayTrait getDisplayTrait() {
    return (DisplayTrait) model;
  }

  @Test
  public void respectsMaximalValueFromRules() throws Exception {
    this.traitRules.setMaximalValue(6);
    assertEquals(6, getDisplayTrait().getMaximalValue());
  }

  @Test
  public void hasBasicTraitType() throws Exception {
    Assert.assertEquals(traitType, getDisplayTrait().getTraitType());
  }

  @Test
  public void removesListenersWhenDisposedOf() throws Exception {
    final boolean[] eventReceived = new boolean[] { false };
    getDisplayTrait().addValueChangeListener(new IChangeListener() {
      @Override
      public void changeOccured() {
        eventReceived[0] = true;
      }
    });
    getDisplayTrait().dispose();
    assertEquals(0, basicTrait.getListenerCount());
  }
}