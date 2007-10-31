package net.sf.anathema.character.core.editors;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.model.ModelExtensionPoint;
import net.sf.anathema.character.core.repository.IModelDisplayConfiguration;
import net.sf.anathema.character.core.resource.CharacterDisplayNameProvider;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;

public class ModelPersistableFactory extends AbstractExecutableExtension implements IElementFactory {
  public static final String PROP_FULL_PATH = "fullPath"; //$NON-NLS-1$

  @Override
  public IAdaptable createElement(IMemento memento) {
    IPath path = new Path(memento.getString(PROP_FULL_PATH));
    IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
    IModelDisplayConfiguration displayConfiguration = new ModelExtensionPoint().getDisplayConfiguration(file);
    IContainer characterFolder = file.getParent();
    try {
      return displayConfiguration.createEditorInput(characterFolder, new CharacterDisplayNameProvider(characterFolder));
    }
    catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return null;
    }
  }
}