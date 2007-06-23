package net.sf.anathema.character.trait;

import static org.junit.Assert.*;
import net.sf.anathema.character.basics.ICharacterBasics;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public abstract class AbstractDisplayTraitTest extends AbstractIntValueModelTest {

  private DummyTraitRules traitRules;

  protected abstract ICharacterBasics createCharacterBasics();

  @Before
  public final void createTrait() {
    ICharacterBasics basics = createCharacterBasics();
    this.traitRules = new DummyTraitRules();
    this.model = new DisplayTrait(new BasicTrait(new Identificate("test")), basics, traitRules); //$NON-NLS-1$
  }
  
  protected final DisplayTrait getDisplayTrait() {
    return (DisplayTrait) model;
  }
  
  @Test
  public void setValueRespectsTraitRules() throws Exception {
    this.traitRules.setCorrectedValue(4);
    model.setValue(2);
    assertEquals(4, model.getValue());
  }
  
  @Test
  public void respectsMaximalValueFromRules() throws Exception {
    this.traitRules.setMaximalValue(6);
    assertEquals(6, getDisplayTrait().getMaximalValue());
  }
}