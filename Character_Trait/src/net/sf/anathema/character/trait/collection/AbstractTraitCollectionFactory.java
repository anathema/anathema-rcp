package net.sf.anathema.character.trait.collection;

import java.util.List;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelInitializer;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelContainer;
import net.sf.anathema.character.core.model.ModelInitializer;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.interactive.IIntValueModel;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;
import net.sf.anathema.character.trait.validator.IValidator;
import net.sf.anathema.character.trait.validator.ValidationUtilities;
import net.sf.anathema.character.trait.validator.ValidatorFactory;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.runtime.CoreException;

public abstract class AbstractTraitCollectionFactory extends
    AbstractModelFactory<ITraitCollectionTemplate, ITraitCollectionModel> {

  private final ValidatorFactory validatorFactory;

  public AbstractTraitCollectionFactory(int defaultMinimumValue) {
    validatorFactory = new ValidatorFactory(defaultMinimumValue);
  }

  @Override
  public final IModelInitializer createInitializer(
      IContentHandle contentHandler,
      ICharacterTemplate template,
      IModelIdentifier identifier) throws PersistenceException, CoreException {
    ITraitCollectionModel model = create(contentHandler, template);
    IModelContainer container = new ModelContainer(ModelCache.getInstance(), identifier.getCharacterId());
    for (IBasicTrait trait : model.getTraits()) {
      List<IValidator> validators = validatorFactory.create(template.getId(), container, trait);
      IIntValueModel creationModel = trait.getCreationModel();
      int correctedValue = ValidationUtilities.getCorrectedValue(validators, creationModel.getValue());
      creationModel.setValue(correctedValue);
    }
    return new ModelInitializer(model, contentHandler, identifier);
  }
}
