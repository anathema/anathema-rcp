package net.sf.anathema.character.abilities.model;

import java.net.URL;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.repository.IEditorInputFactory;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.ui.IEditorInput;

public class AbilitiesEditorInputFactory implements IEditorInputFactory {

  @Override
  public IEditorInput create(
      IFile modelFile,
      ICharacterId characterId,
      URL imageUrl,
      IDisplayNameProvider nameProvider,
      IModelCollection modelProvider) throws PersistenceException, CoreException {
    AbilitiesContext context = AbilitiesContext.create(characterId, modelProvider);
    ICharacterTemplate template = new CharacterTemplateProvider().getTemplate(characterId);
    IFavorizationHandler favorizationHandler = new AbilitiesFavorizationHandler(
        characterId,
        new AbilitiesTemplateProvider().getAttributeTemplate(template.getId()),
        modelProvider);
    return new AbilitiesEditorInput(modelFile, imageUrl, nameProvider, context, favorizationHandler);
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}