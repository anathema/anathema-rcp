package net.sf.anathema.character.core.repository;

import net.sf.anathema.basics.eclipse.runtime.DefaultAdaptable;
import net.sf.anathema.basics.repository.treecontent.deletion.IPageDelible;
import net.sf.anathema.basics.repository.treecontent.deletion.ResourcePageDelible;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.basics.repository.treecontent.itemtype.RegExPrintNameProvider;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.character.internal.CharacterId;
import net.sf.anathema.character.core.model.ModelExtensionPoint;
import net.sf.anathema.character.core.repository.internal.CharacterElementCloseHandler;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class CharacterViewElement implements IViewElement {

  private final IFolder characterFolder;
  private final IViewElement parent;
  private final String unnamedTitle;
  private final ICharacterTemplateProvider templateProvider;
  private final DefaultAdaptable adaptable = new DefaultAdaptable();

  public CharacterViewElement(
      IViewElement parent,
      IFolder characterFolder,
      String unnamedTitle,
      ICharacterTemplateProvider templateProvider) {
    this.parent = parent;
    this.characterFolder = characterFolder;
    this.unnamedTitle = unnamedTitle;
    this.templateProvider = templateProvider;
    initAdaptable();
  }

  private void initAdaptable() {
    adaptable.add(IResource.class, characterFolder);
    adaptable.add(IPageDelible.class, createPageDelible());
  }

  private IPageDelible createPageDelible() {
    CharacterId characterId = new CharacterId(characterFolder);
    CharacterElementCloseHandler closeHandler = new CharacterElementCloseHandler(characterId);
    return new ResourcePageDelible(closeHandler, characterFolder);
  }

  @Override
  public IViewElement[] getChildren() {
    return new ModelExtensionPoint().createViewElements(this, characterFolder, templateProvider);
  }

  @Override
  public String getDisplayName() {
    IFile descriptionFile = characterFolder.getFile(new Path("basic.description")); //$NON-NLS-1$
    return new RegExPrintNameProvider(unnamedTitle).getPrintName(descriptionFile);
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
    return adaptable.getAdapter(adapter);
  }
}