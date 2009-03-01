package net.sf.anathema.character.spiritualtraits.points.virtues;

import static net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants.*;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.TraitCollection;

import org.junit.Before;

public class AbstractVirtuesBonusPoint_Test {

  protected IBasicTrait compassion;
  protected IBasicTrait conviction;
  protected IBasicTrait temperance;
  protected IBasicTrait valor;
  protected IBasicTrait essence;
  protected TraitCollection collection;

  @Before
  public void createCollection() throws Exception {
    this.compassion = createVirtue(COMPASSION_ID);
    this.conviction = createVirtue(CONVICTION_ID);
    this.temperance = createVirtue(TEMPERANCE_ID);
    this.valor = createVirtue(VALOR_ID);
    this.essence = new BasicTrait(ESSENCE_TRAIT_ID);
    collection = new TraitCollection(essence, compassion, conviction, temperance, valor);
  }

  private BasicTrait createVirtue(final String id) {
    final BasicTrait trait = new BasicTrait(id);
    setCreationValue(trait, 1);
    return trait;
  }

  protected final void setCreationValue(final IBasicTrait trait, final int value) {
    trait.getCreationModel().setValue(value);
  }

  protected final void setIncreasedCreationValue(final IBasicTrait trait) {
    setCreationValue(trait, 2);
  }

  protected final void setExperiencedValue(final IBasicTrait trait, final int value) {
    trait.getExperiencedModel().setValue(value);
  }

  protected final void setIncreasedExperienceValue(final IBasicTrait trait) {
    setExperiencedValue(trait, trait.getCreationModel().getValue() + 1);
  }
}