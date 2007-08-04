package net.sf.anathema.character.points;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.points.view.CharacterPointsUpdatable;

import org.eclipse.ui.IEditorInput;
import org.junit.Before;
import org.junit.Test;

public class NonCharactaerInputExperienceUpdateableTest {

  private CharacterPointsUpdatable experienceUpdateable;

  @Before
  public void createUpdateable() throws Exception {
    IEditorInput editedInput = CharacterObjectMother.createNonCharacterEditorInput();
    IPartContainer partContainer = CharacterObjectMother.createPartContainerWithActiveEditorInput(editedInput);
    experienceUpdateable = new CharacterPointsUpdatable(partContainer, null);
  }

  @Test
  public void returnsNullForNonCharacterEditorInput() throws Exception {
    assertNull(experienceUpdateable.getExperience());
  }
}