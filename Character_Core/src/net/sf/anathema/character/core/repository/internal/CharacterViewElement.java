package net.sf.anathema.character.core.repository.internal;

import net.sf.anathema.basics.repository.treecontent.deletion.AbstractPageDelible;
import net.sf.anathema.basics.repository.treecontent.deletion.IPageDelible;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.basics.repository.treecontent.itemtype.RegExPrintNameProvider;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.internal.CharacterId;
import net.sf.anathema.character.core.model.internal.ModelExtensionPoint;
import net.sf.anathema.character.core.template.ICharacterTemplateProvider;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class CharacterViewElement extends AbstractPageDelible implements IViewElement {

  private final IFolder characterFolder;
  private final IViewElement parent;
  private final String unnamedTitle;
  private final ICharacterTemplateProvider templateProvider;

  public CharacterViewElement(
      IViewElement parent,
      IFolder characterFolder,
      String unnamedTitle,
      ICharacterTemplateProvider templateProvider) {
    this.parent = parent;
    this.characterFolder = characterFolder;
    this.unnamedTitle = unnamedTitle;
    this.templateProvider = templateProvider;
  }

  @Override
  public IViewElement[] getChildren() {
    return new ModelExtensionPoint().createViewElements(this, characterFolder, templateProvider);
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
  public ImageDescriptor getImageDescriptor() {
    return parent.getImageDescriptor();
  }

  @Override
  public void openEditor(IWorkbenchPage page) throws PartInitException {
    getChildren()[0].openEditor(page);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object getAdapter(Class adapter) {
    if (adapter.isInstance(characterFolder)) {
      return characterFolder;
    }
    if (adapter == IPageDelible.class) {
      return this;
    }
    return null;
  }

  @Override
  protected void delete() throws CoreException {
    characterFolder.delete(true, new NullProgressMonitor());
  }

  @Override
  protected void closeRelatedEditors(IWorkbenchPage page) throws PartInitException {
    CharacterElementCloseHandler closeHandler = new CharacterElementCloseHandler(getCharacterId());
    for (IEditorReference reference : page.getEditorReferences()) {
      closeHandler.closeIfRequired(reference);
    }
  }

  private ICharacterId getCharacterId() {
    return new CharacterId(characterFolder);
  }
}