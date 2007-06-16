package net.sf.anathema.character.core.repository;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.basics.repository.treecontent.itemtype.RegExPrintNameProvider;
import net.sf.anathema.character.core.CharacterCorePlugin;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IStatus;
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
    List<IViewElement> viewElements = new ArrayList<IViewElement>();
    for (IPluginExtension extension : new EclipseExtensionProvider().getExtensions("net.sf.anathema.character.models")) { //$NON-NLS-1$
      for (IExtensionElement extensionElement : extension.getElements()) {
        try {
          ICharacterModelViewElementFactory factory = extensionElement.getAttributeAsObject("viewElementFactory", //$NON-NLS-1$
              ICharacterModelViewElementFactory.class);
          viewElements.add(factory.create(this, characterFolder));
        }
        catch (ExtensionException e) {
          CharacterCorePlugin.getDefaultInstance().log(IStatus.ERROR, Messages.CharacterViewElement_ModelLoadError, e);
        }
      }
    }
    return viewElements.toArray(new IViewElement[viewElements.size()]);
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