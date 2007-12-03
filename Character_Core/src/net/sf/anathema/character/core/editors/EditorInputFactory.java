package net.sf.anathema.character.core.editors;

import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;
import net.sf.anathema.character.core.repository.IModelDisplayConfiguration;
import net.sf.anathema.character.core.repository.ModelDisplayNameProvider;
import net.sf.anathema.character.core.resource.CharacterDisplayNameProvider;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.IEditorInput;

public class EditorInputFactory {

  public IEditorInput create(IContainer characterFolder, IModelDisplayConfiguration displayConfiguration) {
    try {
      return displayConfiguration.createEditorInput(characterFolder, new ModelDisplayNameProvider(
          displayConfiguration.getDisplayName(),
          new CharacterDisplayNameProvider(characterFolder)));
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