package net.sf.anathema.character.attributes;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;

public class ModelConfiguration implements IModelConfiguration {

  @Override
  public String getDisplayName() {
    return "Attributes";
  }

  @Override
  public String getEditorId() {
    return AttributesEditor.EDITOR_ID;
  }

  @Override
  public IFile getModelFile(IFolder characterFolder) {
    return characterFolder.getFile("attributes.model");
  }

  @Override
  public IEditorInput createEditorInput(
      IFolder characterFolder,
      ImageDescriptor descriptor,
      IDisplayNameProvider provider) throws PersistenceException, CoreException {
    return new AttributesEditorInput(
        getModelFile(characterFolder),
        descriptor,
        provider,
        new AttributeCharacterContext(characterFolder));
  }
}
