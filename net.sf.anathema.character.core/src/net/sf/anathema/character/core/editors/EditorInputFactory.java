package net.sf.anathema.character.core.editors;

import net.sf.anathema.basics.eclipse.logging.ILogger;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.repository.IModelDisplayConfiguration;
import net.sf.anathema.character.core.repository.ModelDisplayNameProvider;

import org.eclipse.core.resources.IContainer;
import org.eclipse.ui.IEditorInput;

public class EditorInputFactory {

  private final ILogger logger;
  private final IDisplayNameProvider displayNameProvider;

  public EditorInputFactory(ILogger logger, IDisplayNameProvider displayNameProvider) {
    this.logger = logger;
    this.displayNameProvider = displayNameProvider;
  }

  public IEditorInput create(IContainer characterFolder, IModelDisplayConfiguration displayConfiguration) {
    try {
      ModelDisplayNameProvider provider = new ModelDisplayNameProvider(
          displayConfiguration.getDisplayName(),
          displayNameProvider);
      return displayConfiguration.createEditorInput(characterFolder, provider);
    }
    catch (Exception e) {
      logger.error(Messages.ModelPersistableFactory_CharacterRestorationErrorMessage, e);
      return null;
    }
  }
}