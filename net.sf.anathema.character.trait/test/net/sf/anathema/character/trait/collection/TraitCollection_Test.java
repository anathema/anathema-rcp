package net.sf.anathema.character.trait.collection;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class TraitCollection_Test {

  private static final String FIRST_TRAIT_ID = "firstTrait";
  private static final String SECOND_TRAIT_ID = "secondTrait";
  private TraitCollection collection;
  private IBasicTrait firstTrait;
  private IBasicTrait secondTrait;

  @Before
  public void createCollectionWithoutSubTrait() {
    firstTrait = new BasicTrait(FIRST_TRAIT_ID);
    secondTrait = new BasicTrait(SECOND_TRAIT_ID);
    collection = new TraitCollection(firstTrait, secondTrait);
  }

  @Test
  public void returnsFirstTraitForFirstTraitId() throws Exception {
    assertThat(collection.getTrait(FIRST_TRAIT_ID), is(firstTrait));
  }

  @Test
  public void returnsSecondTraitForFirstTraitId() throws Exception {
    assertThat(collection.getTrait(SECOND_TRAIT_ID), is(secondTrait));
  }

  @Test
  public void returnsOrderedMultipleTraitsForMultipleIds() throws Exception {
    final IBasicTrait[] traits = collection.getTraits(SECOND_TRAIT_ID, FIRST_TRAIT_ID);
    assertThat(traits.length, is(2));
    assertThat(traits[0], is(secondTrait));
    assertThat(traits[1], is(firstTrait));
  }

  @Test(expected = IllegalArgumentException.class)
  public void throwsExceptionOnRequestForUndefinedTrait() throws Exception {
    collection.getTrait("unknownTrait");
  }

  @Test
  public void containsFirstTrait() throws Exception {
    assertThat(collection.contains(FIRST_TRAIT_ID), is(true));
  }

  @Test
  public void doesNotContainUnknownTrait() throws Exception {
    assertThat(collection.contains("unknown"), is(false));
  }
}