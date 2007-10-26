package net.sf.anathema.character.core.model;

import java.net.URL;

import net.sf.anathema.basics.eclipse.runtime.DefaultAdaptable;
import net.sf.anathema.basics.eclipse.runtime.IProvider;
import net.sf.anathema.basics.jface.FileEditorInput;
import net.sf.anathema.basics.repository.input.IFileItemEditorInput;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelIdentifier;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.resource.ImageDescriptor;

public abstract class AbstractCharacterModelEditorInput<M extends IModel> extends FileEditorInput implements
    IFileItemEditorInput<M> {

  private final IDisplayNameProvider displayNameProvider;
  private final URL imageUrl;

  public AbstractCharacterModelEditorInput(IFile file, URL imageUrl, IDisplayNameProvider displayNameProvider) {
    super(file, ImageDescriptor.createFromURL(imageUrl));
    this.imageUrl = imageUrl;
    this.displayNameProvider = displayNameProvider;
  }
  
  @Override
  public URL getImageDescriptorUrl() {
    return imageUrl;
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

  @Override
  public String getName() {
    return displayNameProvider.getDisplayName();
  }
  
  protected abstract IModelIdentifier getModelIdentifier();
}