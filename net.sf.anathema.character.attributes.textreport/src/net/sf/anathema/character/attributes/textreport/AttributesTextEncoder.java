package net.sf.anathema.character.attributes.textreport;

import net.sf.anathema.character.attributes.model.AttributeMessages;
import net.sf.anathema.character.attributes.util.AttributeDisplayGroupFactory;
import net.sf.anathema.character.trait.display.IDisplayGroupFactory;
import net.sf.anathema.character.trait.textreport.AbstractTraitCollectionTextEncoder;
import net.sf.anathema.lib.util.IIdentificate;


public class AttributesTextEncoder extends AbstractTraitCollectionTextEncoder {

  @Override
  protected String getTraitName(IIdentificate traitType) {
    return AttributeMessages.get(traitType.getId());
  }

  @Override
  protected IDisplayGroupFactory getFactory() {
    return new AttributeDisplayGroupFactory();
  }

  @Override
  protected String getTitle() {
    return Messages.AttributesTextEncoder_AttributesTitle;
  }
}