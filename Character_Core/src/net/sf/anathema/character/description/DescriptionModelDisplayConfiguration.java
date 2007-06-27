package net.sf.anathema.character.description;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.repository.IModelDisplayConfiguration;
import net.sf.anathema.lib.exception.PersistenceException;

public class DescriptionModelDisplayConfiguration implements IModelDisplayConfiguration {

  @Override
  public IEditorInput createEditorInput(
      IFolder characterFolder,
      ImageDescriptor descriptor,
      IDisplayNameProvider provider) throws PersistenceException, CoreException {
    return new CharacterDescriptionEditorInput(getModelFile(characterFolder), descriptor);
  }

  @Override
  public String getDisplayName() {
    return Messages.CharacterDescriptionViewElement_DisplayName;
  }

  @Override
  public String getEditorId() {
    return CharacterDescriptionEditor.EDITOR_ID;
  }

  @Override
  public IFile getModelFile(IFolder characterFolder) {
    return characterFolder.getFile("basic.description");
  }

}
