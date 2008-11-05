package net.sf.anathema.character.core.model;

import java.io.IOException;
import java.net.URL;

import net.disy.commons.core.provider.IProvider;
import net.sf.anathema.basics.jface.FileEditorInput;
import net.sf.anathema.basics.repository.input.IFileItemEditorInput;
import net.sf.anathema.basics.repository.input.ItemFileWriter;
import net.sf.anathema.basics.repository.linkage.util.ILink;
import net.sf.anathema.basics.repository.linkage.util.ResourceLinkProvider;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.editors.ModelPersistable;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IPersistableElement;

public abstract class AbstractCharacterModelEditorInput<M extends IModel> extends FileEditorInput implements
    IFileItemEditorInput<M> {

  private final IDisplayNameProvider displayNameProvider;
  private final ModelPersistable persistable;
  private final IModelPersister< ? , M> persister;

  public AbstractCharacterModelEditorInput(
      IFile file,
      URL imageUrl,
      IDisplayNameProvider displayNameProvider,
      IModelPersister< ? , M> persister) {
    super(file, imageUrl);
    this.displayNameProvider = displayNameProvider;
    this.persistable = new ModelPersistable(file.getFullPath());
    this.persister = persister;
    set(ILink.class, new ResourceLinkProvider(this));
    set(IModelIdentifier.class, new IProvider<IModelIdentifier>() {
      @Override
      public IModelIdentifier getObject() {
        return getModelIdentifier();
      }
    });
  }

  @Override
  protected final IPersistableElement getPersistableInternal() {
    return persistable;
  }

  @Override
  public final M save(IProgressMonitor monitor) throws IOException, CoreException, PersistenceException {
    M model = getItem();
    new ItemFileWriter().saveToFile(getFile(), persister, model, monitor);
    return model;
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