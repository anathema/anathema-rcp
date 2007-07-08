package net.sf.anathema.character.core.model;

import net.sf.anathema.basics.jface.FileEditorInput;
import net.sf.anathema.basics.repository.input.IFileItemEditorInput;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.resource.ImageDescriptor;

public abstract class AbstractCharacterModelEditorInput<M extends IModel> extends FileEditorInput implements
    IFileItemEditorInput<M> {

  public AbstractCharacterModelEditorInput(IFile file, ImageDescriptor imageDescriptor) {
    super(file, imageDescriptor);
  }

  @Override
  public Object getAdapter(Class adapter) {
    if (IModelIdentifier.class.isAssignableFrom(adapter)) {
      return getModelIdentifier();
    }
    return super.getAdapter(adapter);
  }

  protected abstract IModelIdentifier getModelIdentifier();
}