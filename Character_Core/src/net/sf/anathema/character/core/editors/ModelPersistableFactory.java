package net.sf.anathema.character.core.editors;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.basics.item.editor.ErrorMessageEditorInput;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.character.internal.CharacterId;
import net.sf.anathema.character.core.model.ModelExtensionPoint;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;
import net.sf.anathema.character.core.repository.IModelDisplayConfiguration;
import net.sf.anathema.character.core.resource.CharacterDisplayNameProvider;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;

public class ModelPersistableFactory extends AbstractExecutableExtension implements IElementFactory {
  public static final String PROP_FULL_PATH = "fullPath"; //$NON-NLS-1$
  private final IContainer root;
  private final ICharacterTemplateProvider templateProvider;

  public ModelPersistableFactory() {
    this(ResourcesPlugin.getWorkspace().getRoot(), new CharacterTemplateProvider());
  }

  public ModelPersistableFactory(IContainer root, ICharacterTemplateProvider templateProvider) {
    this.root = root;
    this.templateProvider = templateProvider;
  }

  @Override
  public IAdaptable createElement(IMemento memento) {
    IPath path = new Path(memento.getString(PROP_FULL_PATH));
    IFile file = root.getFile(path);
    IContainer characterFolder = file.getParent();
    if (!templateProvider.isTemplateAvailable(new CharacterId(characterFolder))) {
      return new ErrorMessageEditorInput(NLS.bind(
          Messages.ModelPersistableFactory_NoTemplateAvailableMessage,
          characterFolder.getLocation()));
    }
    IModelDisplayConfiguration displayConfiguration = new ModelExtensionPoint().getDisplayConfiguration(file);
    try {
      return displayConfiguration.createEditorInput(characterFolder, new CharacterDisplayNameProvider(characterFolder));
    }
    catch (Exception e) {
      CharacterCorePlugin.getDefaultInstance().log(
          IStatus.ERROR,
          Messages.ModelPersistableFactory_CharacterRestorationErrorMessage,
          e);
      return null;
    }
  }
}