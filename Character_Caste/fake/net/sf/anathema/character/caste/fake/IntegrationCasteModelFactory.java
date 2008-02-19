package net.sf.anathema.character.caste.fake;

import net.sf.anathema.character.caste.ICasteModel;
import net.sf.anathema.character.caste.model.CasteModel;
import net.sf.anathema.character.caste.persistence.CasteModelFactory;
import net.sf.anathema.character.core.character.ICharacterTemplate;

public class IntegrationCasteModelFactory {

  public static ICasteModel createCasteModel(ICharacterTemplate template) {
    return new CasteModel(new CasteModelFactory().createModelTemplate(template));
  }
}