package net.sf.anathema.character.points;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.character.core.CharacterObjectMother;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.experience.Experience;
import net.sf.anathema.character.experience.IExperience;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorInput;
import org.junit.Before;
import org.junit.Test;

public class CharacterInputExperienceUpdateableTest {

  private ExperienceUpdatable experienceUpdateable;
  private IExperience experience;

  @Before
  public void createUpdateable() throws Exception {
    this.experience = new Experience();
    ICharacterId characterId = new DummyCharacterId();
    IEditorInput editedInput = CharacterObjectMother.createCharacterEditorInput(new ModelIdentifier(characterId, "Egal")); //$NON-NLS-1$
    IPartContainer partContainer = CharacterObjectMother.createPartContainerWithActiveEditorInput(editedInput);
    IModelProvider provider = EasyMock.createMock(IModelProvider.class);
    EasyMock.expect(provider.getModel(new ModelIdentifier(characterId, IExperience.MODEL_ID))).andReturn(experience);
    EasyMock.replay(provider);
    experienceUpdateable = new ExperienceUpdatable(partContainer, provider);
  }

  @Test
  public void returnsExperience() throws Exception {
    assertSame(experience, experienceUpdateable.getExperience());
  }

  @Test
  public void experienceIsUpdated() throws Exception {
    // Nice Mock Returns null on second call
    experienceUpdateable.update();
    assertNull(experienceUpdateable.getExperience());
  }
}