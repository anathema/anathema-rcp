package net.sf.anathema.character.attributes;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.character.basics.CharacterBasics;
import net.sf.anathema.character.basics.ICharacterBasics;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.core.repository.AbstractCharacterModelViewElement;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class AttributesViewElement extends AbstractCharacterModelViewElement {

  private final IFolder characterFolder;

  public AttributesViewElement(IViewElement parent, IFolder characterFolder) {
    super(parent, characterFolder);
    this.characterFolder = characterFolder;
  }

  @Override
  public String getDisplayName() {
    return "Attributes";
  }

  @Override
  public void openEditor(IWorkbenchPage page) throws PartInitException {
    try {
      IEditorInput input = new AttributesEditorInput(
          getEditFile(),
          getImageDescriptor(),
          getParent(),
          new AttributeCharacterContext(characterFolder));
      page.openEditor(input, AttributesEditor.EDITOR_ID);
    }
    catch (Exception e) {
      throw createEditorInputException(e);
    }
  }

  @Override
  protected IFile getEditFile() {
    return getFile("attributes.model"); //$NON-NLS-1$
  }
}