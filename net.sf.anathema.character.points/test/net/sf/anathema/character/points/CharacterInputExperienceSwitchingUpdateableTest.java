package net.sf.anathema.character.points;

import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.core.fake.DummyCharacterId;
import net.sf.anathema.character.core.fake.DummyModelCollection;
import net.sf.anathema.character.experience.Experience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.points.view.CharacterPointsUpdatable;
import net.sf.anathema.lib.ui.IUpdatable;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorInput;
import org.junit.Before;
import org.junit.Test;

public class CharacterInputExperienceSwitchingUpdateableTest {

  private CharacterPointsUpdatable experienceUpdateable;
  private IUpdatable modelChangeUpdatable;
  private DummyModelCollection modelCollection;

  @Before
  public void createUpdateable() throws Exception {
    this.modelCollection = new DummyModelCollection();
    this.modelCollection.modelsById.put(IExperience.MODEL_ID, new Experience());
    ICharacterId characterId = new DummyCharacterId();
    IEditorInput editedInput = CharacterObjectMother.createCharacterEditorInput(new ModelIdentifier(characterId, "Egal")); //$NON-NLS-1$
    IPartContainer partContainer = CharacterObjectMother.createPartContainerWithActiveEditorInput(editedInput);
    modelChangeUpdatable = EasyMock.createStrictMock(IUpdatable.class);
    experienceUpdateable = new CharacterPointsUpdatable(partContainer, modelChangeUpdatable, modelCollection);
  }

  @Test
  public void noNotificationForChangesOnOldModel() throws Exception {
    IExperience oldExperience = (IExperience) modelCollection.modelsById.get(IExperience.MODEL_ID);
    modelCollection.modelsById.put(IExperience.MODEL_ID, new Experience());
    experienceUpdateable.update();
    EasyMock.replay(modelChangeUpdatable);
    oldExperience.setExperienced(!oldExperience.isExperienced());
    EasyMock.verify(modelChangeUpdatable);
  }
}