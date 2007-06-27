package net.sf.anathema.character.core.repository;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

// TODO Generic CharacterModelViewElement
/*
 * Draft: Generic character model view element displayname via extension point filename via extension point editor id
 * via extension point editorinputfactory via extension point. create(editfile, imagedescriptor, nameprovider)
 */
public class ModelViewElement extends AbstractCharacterModelViewElement {

  private final IFolder characterFolder;
  private final IModelConfiguration configuration;

  public ModelViewElement(IViewElement parent, IFolder characterFolder, IModelConfiguration configuration) {
    super(parent, characterFolder);
    this.characterFolder = characterFolder;
    this.configuration = configuration;
  }

  @Override
  public String getDisplayName() {
    return configuration.getDisplayName();
  }

  @Override
  public void openEditor(IWorkbenchPage page) throws PartInitException {
    try {
      IEditorInput input = configuration.createEditorInput(characterFolder, getImageDescriptor(), getParent());
      page.openEditor(input, configuration.getEditorId());
    }
    catch (Exception e) {
      throw createEditorInputException(e);
    }
  }

  @Override
  protected IFile getEditFile() {
    return configuration.getModelFile(characterFolder);
  }
}