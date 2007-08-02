package net.sf.anathema.character.core.model;

import net.sf.anathema.basics.eclipse.runtime.DefaultAdaptable;
import net.sf.anathema.basics.eclipse.runtime.IProvider;
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
  protected void initDefaultAdaptable(DefaultAdaptable defaultAdaptable) {
    super.initDefaultAdaptable(defaultAdaptable);
    defaultAdaptable.add(IModelIdentifier.class, new IProvider<IModelIdentifier>() {
      @Override
      public IModelIdentifier get() {
        return getModelIdentifier();
      }
    });
  }

  protected abstract IModelIdentifier getModelIdentifier();
}