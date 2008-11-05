package net.sf.anathema.character.core.repository.internal;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.provider.IProvider;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.runtime.DefaultAdaptable;
import net.sf.anathema.basics.eclipse.ui.IEditorInputProvider;
import net.sf.anathema.basics.repository.linkage.util.ILink;
import net.sf.anathema.basics.repository.linkage.util.ResourceLinkProvider;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.character.core.model.IConfigurableViewElement;
import net.sf.anathema.character.core.repository.IModelDisplayConfiguration;
import net.sf.anathema.character.core.resource.CharacterModelEditorOpener;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class CharacterModelViewElement implements IConfigurableViewElement {

  private final IViewElement parent;
  private final IFolder folder;
  private final IFolder characterFolder;
  private final IModelDisplayConfiguration configuration;
  private final DefaultAdaptable adaptable = new DefaultAdaptable();
  private final CharacterModelEditorOpener editorOpener = new CharacterModelEditorOpener();
  private final List<IViewElement> children = new ArrayList<IViewElement>();

  public CharacterModelViewElement(
      IViewElement parent,
      IFolder characterFolder,
      IModelDisplayConfiguration configuration) {
    this.parent = parent;
    folder = characterFolder;
    this.characterFolder = characterFolder;
    this.configuration = configuration;
    initAdaptable();
  }

  private void initAdaptable() {
    adaptable.set(IResource.class, new IProvider<IResource>() {
      @Override
      public IResource getObject() {
        return configuration.getModelFile(characterFolder);
      }
    });
    adaptable.set(ILink.class, new ResourceLinkProvider(adaptable));
    adaptable.set(IEditorInputProvider.class, new IProvider<IEditorInputProvider>() {
      @Override
      public IEditorInputProvider getObject() {
        return new IEditorInputProvider() {
          @Override
          public IEditorInput getEditorInput() throws PersistenceException, CoreException, ExtensionException {
            return createEditorInput();
          }
        };
      }
    });
  }

  public IEditorInput createEditorInput() throws PersistenceException, CoreException, ExtensionException {
    return editorOpener.createEditorInput(characterFolder, configuration);
  }

  @Override
  public void addChild(IViewElement child) {
    children.add(child);
  }

  @Override
  public final IViewElement[] getChildren() {
    return children.toArray(new IViewElement[children.size()]);
  }

  @Override
  public URL getImageUrl() {
    return configuration.getImageUrl();
  }

  @Override
  public final IViewElement getParent() {
    return parent;
  }

  @Override
  public final boolean hasChildren() {
    return !children.isEmpty();
  }

  @Override
  public final boolean equals(Object object) {
    if (object == null || object.getClass() != getClass()) {
      return false;
    }
    CharacterModelViewElement other = (CharacterModelViewElement) object;
    return folder.equals(other.folder) && configuration.equals(other.configuration);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object getAdapter(Class adapter) {
    return adaptable.getAdapter(adapter);
  }

  @Override
  public String getDisplayName() {
    return configuration.getDisplayName();
  }

  @Override
  public void openEditor(IWorkbenchPage page) throws PartInitException {
    editorOpener.openEditor(page, characterFolder, configuration);
  }

  @Override
  public void openEditorForChild(IWorkbenchPage page, IEditorInput editorInput) throws PartInitException {
    editorOpener.openEditor(page, configuration, editorInput);
  }
}