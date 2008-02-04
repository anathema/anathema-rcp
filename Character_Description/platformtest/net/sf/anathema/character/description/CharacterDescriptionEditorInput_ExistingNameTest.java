package net.sf.anathema.character.description;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.basics.eclipse.resource.fake.ResourceObjectMother;

import org.eclipse.core.resources.IFile;
import org.junit.Before;
import org.junit.Test;

public class CharacterDescriptionEditorInput_ExistingNameTest {

  private CharacterDescription description;
  private CharacterDescriptionEditorInput input;

  @Before
  public void createEditorInput() throws Exception {
    description = new CharacterDescription();
    IFile file = ResourceObjectMother.createNonExistingFile();
    input = new CharacterDescriptionEditorInput(file, null, description);
  }

  @Test
  public void returnsModelNameFollowedByCharacterName() throws Exception {
    description.getName().setText("Name"); //$NON-NLS-1$
    assertEquals("Description - Name", input.getName()); //$NON-NLS-1$
  }
}