package net.sf.anathema.character.description;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.basics.eclipse.resource.fake.ResourceObjectMother;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.resource.CharacterDisplayNameProvider;
import net.sf.anathema.character.core.resource.FakeCharacterTemplateProvider;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.junit.Before;
import org.junit.Test;

public class CharacterDescriptionEditorInput_MissingNameTest {

  private CharacterDescription description;
  private CharacterDescriptionEditorInput input;
  private IFile file;
  private CharacterDisplayNameProvider characterDisplayNameProvider;

  @Before
  public void createEditorInput() throws Exception {
    description = new CharacterDescription();
    IContainer parent = ResourceObjectMother.createNamedContainer("Folder"); //$NON-NLS-1$
    file = ResourceObjectMother.createFile(parent);
    ICharacterTemplateProvider provider = new FakeCharacterTemplateProvider();
    characterDisplayNameProvider = new CharacterDisplayNameProvider(file.getParent(), provider);
    input = new CharacterDescriptionEditorInput(file, null, description, characterDisplayNameProvider);
  }

  @Test
  public void returnsModelNameFollowedByDefaultNameIfCharacterNameIsNull() throws Exception {
    String fallbackName = characterDisplayNameProvider.getFallbackName();
    assertEquals("Description - " + fallbackName, input.getName()); //$NON-NLS-1$
  }
}