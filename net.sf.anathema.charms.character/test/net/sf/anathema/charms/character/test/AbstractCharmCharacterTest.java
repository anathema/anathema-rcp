package net.sf.anathema.charms.character.test;

import net.sf.anathema.character.core.fake.DummyCharacter;
import net.sf.anathema.character.experience.Experience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.charms.character.model.CharmModel;
import net.sf.anathema.charms.character.model.ICharmModel;

import org.junit.Before;

public class AbstractCharmCharacterTest {

  protected DummyCharacter character;

  @Before
  public void createCharacter() throws Exception {
    character = new DummyCharacter();
    character.modelsById.put(IExperience.MODEL_ID, new Experience());
    character.modelsById.put(ICharmModel.MODEL_ID, new CharmModel());
  }

  protected final CharmModel getCharmModel() {
    return (CharmModel) character.getModel(ICharmModel.MODEL_ID);
  }

  protected final Experience getExperience() {
    return (Experience) character.getModel(IExperience.MODEL_ID);
  }
}