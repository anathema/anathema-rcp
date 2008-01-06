package net.sf.anathema.character.caste.persistence;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.caste.model.CasteProvider;
import net.sf.anathema.character.caste.model.CasteTemplate;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelPersister;

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
    return new CasteTemplate(ArrayUtilities.transform(
        provider.getCastes(template.getCharacterTypeId()),
        String.class,
        new CasteToIdTransformer()));
  }

  @Override
  protected IModelPersister<CasteTemplate, ? > getPersister() {
    return persister;
  }
}