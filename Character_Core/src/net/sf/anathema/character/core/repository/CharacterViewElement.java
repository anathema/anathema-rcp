package net.sf.anathema.character.core.repository;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.basics.repository.treecontent.itemtype.RegExPrintNameProvider;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class CharacterViewElement implements IViewElement {

  private final IFolder characterFolder;
  private final IViewElement parent;
  private final String unnamedTitle;

  public CharacterViewElement(IViewElement parent, IFolder characterFolder, String unnamedTitle) {
    this.parent = parent;
    this.characterFolder = characterFolder;
    this.unnamedTitle = unnamedTitle;
  }

  @Override
  public IViewElement[] getChildren() {
    return new IViewElement[] { new CharacterDescriptionViewElement(this, characterFolder) };
  }

  @Override
  public String getDisplayName() {
    IFile descriptionFile = characterFolder.getFile(new Path("basic.description")); //$NON-NLS-1$
    if (descriptionFile.exists()) {
      // TODO Soll der Fallbackmechanismus aus dem RegExPrintNameProvider hier verwendet werden?
      return new RegExPrintNameProvider().getPrintName(descriptionFile);
    }
    return unnamedTitle;
  }

  @Override
  public IViewElement getParent() {
    return parent;
  }

  @Override
  public boolean hasChildren() {
    return true;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof CharacterViewElement)) {
      return false;
    }
    CharacterViewElement other = (CharacterViewElement) object;
    return characterFolder.equals(other.characterFolder);
  }

  @Override
  public int hashCode() {
    return characterFolder.hashCode();
  }

  @Override
  public Image getImage() {
    return parent.getImage();
  }

  @Override
  public void openEditor(IWorkbenchPage page) throws PartInitException {
    getChildren()[0].openEditor(page);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object getAdapter(Class adapter) {
    return null;
  }
}