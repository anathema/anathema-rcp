package net.sf.anathema.charms.character.evaluation;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.fake.DummyTraitCollection;
import net.sf.anathema.character.trait.model.IMainTraitModelProvider;
import net.sf.anathema.charms.character.test.AbstractCharmCharacterTest;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class GenericCharmCharacter_Test extends AbstractCharmCharacterTest {

  private static final String GENERIC_PATTERN = "generic {0}";
  private static final String SECOND_ID = "Second";
  private static final String FIRST_ID = "First";
  private static final String MODEL_ID = "CharmTraits";
  private GenericCharmCharacter charmCharacter;

  @Before
  public void createGenericCharmCharacter() throws Exception {
    DummyTraitCollection traitCollection = new DummyTraitCollection();
    traitCollection.addTrait(new BasicTrait(FIRST_ID));
    traitCollection.addTrait(new BasicTrait(SECOND_ID));
    character.modelsById.put(MODEL_ID, traitCollection);
    charmCharacter = new GenericCharmCharacter(character, createTraitModelProvider());
  }

  private IMainTraitModelProvider createTraitModelProvider() {
    IMainTraitModelProvider modelProvider = createMock(IMainTraitModelProvider.class);
    expect(modelProvider.getFor(character.characterType.typeId)).andStubReturn(MODEL_ID);
    replay(modelProvider);
    return modelProvider;
  }

  @Test
  public void returnsOneCharmIdWithAccordingPrimaryTraitForEachTraitInCollection() throws Exception {
    List<ICharmId> allIds = charmCharacter.getAllCharmIds(GENERIC_PATTERN);
    assertThat(allIds.size(), is(2));
    assertThat(allIds.get(0).getPrimaryTrait(), is(FIRST_ID));
    assertThat(allIds.get(1).getPrimaryTrait(), is(SECOND_ID));
  }

  @Test
  public void returnsTraitIdsWithCorrectGenericPattern() throws Exception {
    List<ICharmId> allIds = charmCharacter.getAllCharmIds(GENERIC_PATTERN);
    assertThat(allIds.get(0).getIdPattern(), is(GENERIC_PATTERN));
    assertThat(allIds.get(1).getIdPattern(), is(GENERIC_PATTERN));
  }

  @Test
  public void returnsOnlyLearnedCharmsIfAsked() throws Exception {
    getCharmModel().toggleCreationLearned(new CharmId(GENERIC_PATTERN, FIRST_ID));
    List<ICharmId> learnedIds = charmCharacter.getLearnedCharmIds(GENERIC_PATTERN);
    assertThat(learnedIds.size(), is(1));
    assertThat(learnedIds.get(0).getPrimaryTrait(), is(FIRST_ID));
  }
}