package net.sf.anathema.character.attributes.textreport;

import java.util.List;

import net.sf.anathema.character.attributes.model.AttributeMessages;
import net.sf.anathema.character.attributes.util.AttributeDisplayUtilties;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.textreport.encoder.ITextReportEncoder;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.textreport.AbstractTraitCollectionTextEncoder;
import net.sf.anathema.lib.util.IIdentificate;


public class AttributesTextEncoder extends AbstractTraitCollectionTextEncoder implements ITextReportEncoder {

  @Override
  protected String getTraitName(IIdentificate traitType) {
    return AttributeMessages.get(traitType.getId());
  }

  @Override
  protected List<IDisplayTraitGroup<IDisplayTrait>> getDisplayGroups(ICharacter character) {
    return AttributeDisplayUtilties.getDisplayAttributeGroups(character);
  }

  @Override
  protected String getTitle() {
    return Messages.AttributesTextEncoder_AttributesTitle;
  }

  @Override
  public String getModelId() {
    return "net.sf.anathema.character.attributes.model"; //$NON-NLS-1$
  }
}