package net.sf.anathema.character.core.repository;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.character.core.CharacterCorePlugin;
import net.sf.anathema.character.description.CharacterDescriptionEditor;
import net.sf.anathema.character.description.CharacterDescriptionEditorInput;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class CharacterDescriptionViewElement implements IViewElement {

  private final IViewElement parent;
  private final IFolder characterFolder;

  public CharacterDescriptionViewElement(IViewElement parent, IFolder characterFolder) {
    this.parent = parent;
    this.characterFolder = characterFolder;
  }

  @Override
  public IViewElement[] getChildren() {
    return new IViewElement[0];
  }

  @Override
  public String getDisplayName() {
    return "Description";
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof CharacterDescriptionViewElement)) {
      return false;
    }
    CharacterDescriptionViewElement other = (CharacterDescriptionViewElement) object;
    return characterFolder.equals(other.characterFolder);
  }

  @Override
  public Image getImage() {
    return parent.getImage();
  }

  @Override
  public IViewElement getParent() {
    return parent;
  }

  @Override
  public boolean hasChildren() {
    return false;
  }

  @Override
  public void openEditor(IWorkbenchPage page) throws PartInitException {
    IFile file = characterFolder.getFile(new Path("basic.description")); //$NON-NLS-1$
    try {
      IEditorInput input;
      input = new CharacterDescriptionEditorInput(file, ImageDescriptor.createFromImage(getImage()));
      page.openEditor(input, CharacterDescriptionEditor.EDITOR_ID);
    }
    catch (PersistenceException e) {
      throw new PartInitException(new Status(
          IStatus.ERROR,
          CharacterCorePlugin.PLUGIN_ID,
          "Failed to create EditorInput.",
          e));
    }
    catch (CoreException e) {
      throw new PartInitException(new Status(
          IStatus.ERROR,
          CharacterCorePlugin.PLUGIN_ID,
          "Failed to create EditorInput.",
          e));
    }
  }

  @Override
  public Object getAdapter(Class adapter) {
    return null;
  }
}