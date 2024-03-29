package net.sf.anathema.character.caste.model;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.model.IExecutableModelSupporter;

public class CasteModelSupporter extends UnconfiguredExecutableExtension implements IExecutableModelSupporter {

  @Override
  public boolean isSupportedBy(ICharacterTemplate template) {
    return new CasteCollection().getCastes(template.getCharacterTypeId()).length > 0;
  }
}