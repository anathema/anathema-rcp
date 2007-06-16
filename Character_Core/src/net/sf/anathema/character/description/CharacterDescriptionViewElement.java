package net.sf.anathema.character.description;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.character.core.repository.AbstractCharacterModelViewElement;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class CharacterDescriptionViewElement extends AbstractCharacterModelViewElement {

  public CharacterDescriptionViewElement(IViewElement parent, IFolder characterFolder) {
    super(parent, characterFolder);
  }

  @Override
  public String getDisplayName() {
    return Messages.CharacterDescriptionViewElement_DisplayName;
  }

  @Override
  public void openEditor(IWorkbenchPage page) throws PartInitException {
    try {
      IEditorInput input = new CharacterDescriptionEditorInput(
          getFile("basic.description"), ImageDescriptor.createFromImage(getImage())); //$NON-NLS-1$
      page.openEditor(input, CharacterDescriptionEditor.EDITOR_ID);
    }
    catch (Exception e) {
      throw createEditorInputException(e);
    }
  } 
}