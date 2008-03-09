package net.sf.anathema.character.abilities.textreport;

import java.util.List;

import net.sf.anathema.character.abilities.util.AbilitiesDisplayUtilties;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.textreport.encoder.ITextReportEncoder;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.textreport.AbstractTraitCollectionTextEncoder;
import net.sf.anathema.lib.util.IIdentificate;


public class AbilitiesTextEncoder extends AbstractTraitCollectionTextEncoder implements ITextReportEncoder {

  @Override
  protected String getTraitName(IIdentificate traitType) {
    return traitType.getId();
  }

  @Override
  protected List<IDisplayTraitGroup<IDisplayTrait>> getDisplayGroups(ICharacter character) {
    return AbilitiesDisplayUtilties.getDisplayAttributeGroups(character);
  }

  @Override
  protected String getTitle() {
    return "Abilities:";
  }

  @Override
  public String getModelId() {
    return IAbilitiesPluginConstants.MODEL_ID;
  }
}