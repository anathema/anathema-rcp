package net.sf.anathema.character.attributes;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.character.core.repository.AbstractCharacterModelViewElement;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class AttributesViewElement extends AbstractCharacterModelViewElement {

  public AttributesViewElement(IViewElement parent, IFolder characterFolder) {
    super(parent, characterFolder);
  }

  @Override
  public String getDisplayName() {
    return "Attributes";
  }

  @Override
  public void openEditor(IWorkbenchPage page) throws PartInitException {
    try {
      IEditorInput input = new AttributesEditorInput(
          getFile("attributes.model"), ImageDescriptor.createFromImage(getImage()), getParent()); //$NON-NLS-1$
      page.openEditor(input, AttributesEditor.EDITOR_ID);
    }
    catch (Exception e) {
      throw createEditorInputException(e);
    }
  }
}