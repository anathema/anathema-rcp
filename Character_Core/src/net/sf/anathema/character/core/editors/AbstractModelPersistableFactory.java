package net.sf.anathema.character.core.editors;

import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.character.internal.CharacterId;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;

public abstract class AbstractModelPersistableFactory implements IElementFactory {
  public static final String PROP_FULL_PATH = "fullPath"; //$NON-NLS-1$
  private final IModelCollection modelCollection;

  public AbstractModelPersistableFactory(IModelCollection modelCollection) {
    this.modelCollection = modelCollection;
  }
  
  protected final IModel getModel(IMemento memento, String modelId) {
    IFile file = getModelFile(memento);
    ModelIdentifier identifier = new ModelIdentifier(new CharacterId(file.getParent()), modelId);
    return modelCollection.getModel(identifier);
  }

  protected final IFile getModelFile(IMemento memento) {
    IPath path = new Path(memento.getString(PROP_FULL_PATH));
    return ResourcesPlugin.getWorkspace().getRoot().getFile(path);
  }
}