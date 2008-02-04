package net.sf.anathema.character.description;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.basics.eclipse.resource.fake.ResourceObjectMother;
import net.sf.anathema.character.core.resource.CharacterDisplayNameProvider;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.junit.Before;
import org.junit.Test;

public class CharacterDescriptionEditorInput_MissingNameTest {

  private CharacterDescription description;
  private CharacterDescriptionEditorInput input;
  private IFile file;

  @Before
  public void createEditorInput() throws Exception {
    description = new CharacterDescription();
    IContainer parent = ResourceObjectMother.createNamedContainer("Folder"); //$NON-NLS-1$
    file = ResourceObjectMother.createFile(parent);
    input = new CharacterDescriptionEditorInput(file, null, description);
  }

  @Test
  public void returnsModelNameFollowedByDefaultNameIfCharacterNameIsNull() throws Exception {
    String displayName = new CharacterDisplayNameProvider(file.getParent()).getDisplayName();
    assertEquals("Description - " + displayName, input.getName()); //$NON-NLS-1$
  }
}