package net.sf.anathema.character.caste.persistence;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.character.caste.model.CasteProvider;
import net.sf.anathema.character.caste.model.CasteTemplate;
import net.sf.anathema.character.caste.model.ICasteModel;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelInitializer;
import net.sf.anathema.character.core.model.IModelPersister;

public class CasteModelFactory extends AbstractModelFactory<CasteTemplate, ICasteModel> {
  private final CasteModelPersister persister = new CasteModelPersister();
  private final ICasteProvider provider;

  public CasteModelFactory() {
    this(new CasteProvider());
  }

  public CasteModelFactory(ICasteProvider provider) {
    this.provider = provider;
  }

  @Override
  public CasteTemplate createModelTemplate(ICharacterTemplate template) {
    return new CasteTemplate(provider.getCastes(template.getCharacterTypeId()));
  }

  @Override
  public IModelInitializer createInitializer(
      final ICasteModel model,
      IContentHandle file,
      final IModelIdentifier identifier) {
    return new CasteModelInitializer(model, file, identifier, identifier, model);
  }

  @Override
  protected IModelPersister<CasteTemplate, ICasteModel> getPersister() {
    return persister;
  }
}