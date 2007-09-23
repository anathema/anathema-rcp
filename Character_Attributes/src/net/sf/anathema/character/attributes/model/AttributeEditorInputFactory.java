package net.sf.anathema.character.attributes.model;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.repository.IEditorInputFactory;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.core.template.ICharacterTemplate;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;

public class AttributeEditorInputFactory implements IEditorInputFactory {

  @Override
  public IEditorInput create(
      IFile modelFile,
      ICharacterId characterId,
      ImageDescriptor descriptor,
      IDisplayNameProvider nameProvider,
      IModelProvider modelProvider) throws PersistenceException, CoreException {
    AttributesContext context = AttributesContext.create(characterId, modelProvider);
    ICharacterTemplate template = new CharacterTemplateProvider().getTemplate(characterId);
    IFavorizationHandler favorizationHandler = new AttributeFavorizationHandler(
        characterId,
        new AttributeTemplateProvider().getAttributeTemplate(template.getId()),
        modelProvider);
    return new AttributesEditorInput(modelFile, descriptor, nameProvider, context, favorizationHandler);
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}