package net.sf.anathema.character.points;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.core.fake.DummyCharacterId;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.points.view.CharacterPointsUpdatable;
import net.sf.anathema.view.valuelist.IUpdatable;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorInput;
import org.junit.Before;
import org.junit.Test;

public class CharacterInputExperienceUpdateableTest {

  private CharacterPointsUpdatable experienceUpdateable;
  private IExperience experience;
  private IUpdatable modelChangeUpdatable;

  @Before
  public void createUpdateable() throws Exception {
    this.experience = new DummyExperience();
    ICharacterId characterId = new DummyCharacterId();
    IEditorInput editedInput = CharacterObjectMother.createCharacterEditorInput(new ModelIdentifier(characterId, "Egal")); //$NON-NLS-1$
    IPartContainer partContainer = CharacterObjectMother.createPartContainerWithActiveEditorInput(editedInput);
    IModelProvider provider = EasyMock.createMock(IModelProvider.class);
    ModelIdentifier experienceIdentifier = new ModelIdentifier(characterId, IExperience.MODEL_ID);
    EasyMock.expect(provider.getModel(experienceIdentifier)).andReturn(experience).anyTimes();
    EasyMock.replay(provider);
    modelChangeUpdatable = EasyMock.createStrictMock(IUpdatable.class);
    experienceUpdateable = new CharacterPointsUpdatable(partContainer, modelChangeUpdatable, provider);
  }

  @Test
  public void returnsExperience() throws Exception {
    assertSame(experience, experienceUpdateable.getExperience());
  }

  @Test
  public void experienceIsUpdated() throws Exception {
    experience = null;
    experienceUpdateable.update();
    assertNull(experienceUpdateable.getExperience());
  }
  
  @Test
  public void updatableIsNotifiedForModelChange() throws Exception {
    modelChangeUpdatable.update();
    EasyMock.replay(modelChangeUpdatable);
    experience.setExperienced(!experience.isExperienced());
    EasyMock.verify(modelChangeUpdatable);
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