package net.sf.anathema.character.caste.persistence;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.character.caste.model.CasteProvider;
import net.sf.anathema.character.caste.model.CasteTemplate;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.runtime.CoreException;

public class CasteModelFactory extends AbstractModelFactory<CasteTemplate> {
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
  public IModel create(IContentHandle modelContent, ICharacterTemplate template)
      throws PersistenceException,
      CoreException {
    return super.create(modelContent, template);
  }

  @Override
  protected IModelPersister<CasteTemplate, ? > getPersister() {
    return persister;
  }
}