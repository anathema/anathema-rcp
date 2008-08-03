package net.sf.anathema.character.abilities.util;

import static org.junit.Assert.*;

import java.util.List;

import net.sf.anathema.character.abilities.util.TraitListBuilder;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.fake.DummyTraitCollection;

import org.junit.Test;

public class TraitListBuilder_Test {

  @Test
  public void returnsEmptyListIfNoTraitsPresent() throws Exception {
    DummyTraitCollection model = new DummyTraitCollection();
    TraitListBuilder builder = new TraitListBuilder();
    List<IBasicTrait> traits = builder.buildList(model);
    assertTrue(traits.isEmpty());
  }

  @Test
  public void returnsOriginalListIfNoSubtraitsPresent() throws Exception {
    DummyTraitCollection model = new DummyTraitCollection();
    BasicTrait trait = new BasicTrait("Trait"); //$NON-NLS-1$
    model.addTrait(trait);
    TraitListBuilder builder = new TraitListBuilder();
    List<IBasicTrait> traits = builder.buildList(model);
    assertTrue(traits.contains(trait));
    assertEquals(1, traits.size());
  }

  @Test
  public void replacesOriginalTraitsWithConfiguredSubtraits() throws Exception {
    DummyTraitCollection model = new DummyTraitCollection();
    BasicTrait trait = new BasicTrait("Trait"); //$NON-NLS-1$
    model.addTrait(trait);
    BasicTrait subtrait = new BasicTrait("Subtrait"); //$NON-NLS-1$
    model.addSubTrait(trait.getTraitType().getId(), subtrait);
    TraitListBuilder builder = new TraitListBuilder();
    List<IBasicTrait> traits = builder.buildList(model);
    assertTrue(traits.contains(subtrait));
    assertEquals(1, traits.size());
  }
}