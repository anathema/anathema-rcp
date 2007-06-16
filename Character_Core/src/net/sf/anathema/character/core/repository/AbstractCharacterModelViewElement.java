package net.sf.anathema.character.core.repository;

import net.sf.anathema.basics.repository.messages.BasicRepositoryMessages;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.character.core.CharacterCorePlugin;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PartInitException;

public abstract class AbstractCharacterModelViewElement implements IViewElement {

  private final IViewElement parent;
  private final IFolder folder;

  public AbstractCharacterModelViewElement(IViewElement parent, IFolder characterFolder) {
    this.parent = parent;
    this.folder = characterFolder;
  }

  @Override
  public final IViewElement[] getChildren() {
    return new IViewElement[0];
  }

  @Override
  public Image getImage() {
    return parent.getImage();
  }

  @Override
  public final IViewElement getParent() {
    return parent;
  }

  @Override
  public final boolean hasChildren() {
    return false;
  }

  @Override
  public final boolean equals(Object object) {
    if (object == null || object.getClass() == getClass()) {
      return false;
    }
    AbstractCharacterModelViewElement other = (AbstractCharacterModelViewElement) object;
    return folder.equals(other.folder);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object getAdapter(Class adapter) {
    return null;
  }

  protected final IFile getFile(String filename) {
    return folder.getFile(new Path(filename));
  }

  protected final PartInitException createEditorInputException(Exception e) {
    return new PartInitException(new Status(
        IStatus.ERROR,
        CharacterCorePlugin.PLUGIN_ID,
        BasicRepositoryMessages.RepositoryBasics_CreateEditorInputFailedMessage,
        e));
  }
}