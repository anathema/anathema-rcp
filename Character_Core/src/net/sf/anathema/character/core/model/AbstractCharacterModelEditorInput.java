package net.sf.anathema.character.core.model;

import java.net.URL;

import net.sf.anathema.basics.eclipse.runtime.DefaultAdaptable;
import net.sf.anathema.basics.eclipse.runtime.IProvider;
import net.sf.anathema.basics.jface.FileEditorInput;
import net.sf.anathema.basics.repository.input.IFileItemEditorInput;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.editors.ModelPersistable;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.ui.IPersistableElement;

public abstract class AbstractCharacterModelEditorInput<M extends IModel> extends FileEditorInput implements
    IFileItemEditorInput<M> {

  private final IDisplayNameProvider displayNameProvider;
  private final ModelPersistable persistable;

  public AbstractCharacterModelEditorInput(IFile file, URL imageUrl, IDisplayNameProvider displayNameProvider) {
    super(file, imageUrl);
    this.displayNameProvider = displayNameProvider;
    this.persistable = new ModelPersistable(file.getFullPath());
  }
  
  @Override
  public final IPersistableElement getPersistable() {
    return persistable;
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

  protected IFolder getCharacterFolder() {
    return (IFolder) getFile().getParent();
  }

  protected final IModelIdentifier getModelIdentifier() {
    return new ModelIdentifier(getCharacterFolder(), getModelId());
  }

  protected abstract String getModelId();

  @Override
  public String getName() {
    return displayNameProvider.getDisplayName();
  }
}