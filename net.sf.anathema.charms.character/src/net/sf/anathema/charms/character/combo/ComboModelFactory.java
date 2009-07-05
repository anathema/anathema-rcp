package net.sf.anathema.charms.character.combo;

import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.core.model.template.NullModelTemplate;

public class ComboModelFactory extends AbstractModelFactory<NullModelTemplate, IComboModel> {

  private final ComboPersister comboPersister = new ComboPersister();

  @Override
  protected NullModelTemplate createModelTemplate(ICharacterTemplate template) {
    return new NullModelTemplate();
  }

  @Override
  protected IModelPersister<NullModelTemplate, IComboModel> getPersister() {
    return comboPersister;
  }
}