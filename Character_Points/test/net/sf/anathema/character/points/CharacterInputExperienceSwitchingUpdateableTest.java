package net.sf.anathema.character.points;

import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.core.fake.DummyCharacterId;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModel;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.points.view.CharacterPointsUpdatable;
import net.sf.anathema.lib.ui.IUpdatable;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorInput;
import org.junit.Before;
import org.junit.Test;

public class CharacterInputExperienceSwitchingUpdateableTest {

  private CharacterPointsUpdatable experienceUpdateable;
  private IExperience experience;
  private IUpdatable modelChangeUpdatable;

  @Before
  public void createUpdateable() throws Exception {
    this.experience = new DummyExperience();
    ICharacterId characterId = new DummyCharacterId();
    IEditorInput editedInput = CharacterObjectMother.createCharacterEditorInput(new ModelIdentifier(characterId, "Egal")); //$NON-NLS-1$
    IPartContainer partContainer = CharacterObjectMother.createPartContainerWithActiveEditorInput(editedInput);
    IModelProvider provider = new IModelProvider() {
      @Override
      public IModel getModel(IModelIdentifier identifier) {
        return experience;
      }
    };
    modelChangeUpdatable = EasyMock.createStrictMock(IUpdatable.class);
    experienceUpdateable = new CharacterPointsUpdatable(partContainer, modelChangeUpdatable, provider);
  }

  @Test
  public void noNotificationForChangesOnOldModel() throws Exception {
    IExperience oldExperience = experience;
    this.experience = new DummyExperience();
    experienceUpdateable.update();
    EasyMock.replay(modelChangeUpdatable);
    oldExperience.setExperienced(!oldExperience.isExperienced());
    EasyMock.verify(modelChangeUpdatable);
  }
}