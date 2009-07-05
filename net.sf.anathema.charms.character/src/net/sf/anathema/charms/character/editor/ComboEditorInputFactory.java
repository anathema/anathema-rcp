package net.sf.anathema.charms.character.editor;

import java.net.URL;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.repository.IEditorInputFactory;
import net.sf.anathema.charms.character.combo.IComboModel;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorInput;

public class ComboEditorInputFactory extends UnconfiguredExecutableExtension implements IEditorInputFactory {

  @Override
  public IEditorInput create(
      IFile modelFile,
      ICharacterId characterId,
      URL imageUrl,
      IDisplayNameProvider nameProvider,
      IModelCollection modelProvider) throws PersistenceException, CoreException {
    ModelIdentifier identifier = new ModelIdentifier(characterId, IComboModel.MODEL_ID);
    IComboModel combos = (IComboModel) modelProvider.getModel(identifier);
    return new ComboEditorInput(modelFile, imageUrl, combos, nameProvider);
  }
}