package net.sf.anathema.character.points;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.character.core.CharacterObjectMother;

import org.eclipse.ui.IEditorInput;
import org.junit.Before;
import org.junit.Test;

public class NonCharactaerInputExperienceUpdateableTest {

  private ExperienceUpdatable experienceUpdateable;

  @Before
  public void createUpdateable() throws Exception {
    IEditorInput editedInput = CharacterObjectMother.createNonCharacterEditorInput();
    IPartContainer partContainer = CharacterObjectMother.createPartContainerWithActiveEditorInput(editedInput);
    experienceUpdateable = new ExperienceUpdatable(partContainer, null);
  }

  @Test
  public void returnsNullForNonCharacterEditorInput() throws Exception {
    assertNull(experienceUpdateable.getExperience());
  }
}