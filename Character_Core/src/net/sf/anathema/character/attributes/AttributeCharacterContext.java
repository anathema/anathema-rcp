package net.sf.anathema.character.attributes;

import net.sf.anathema.character.basics.ICharacterBasics;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.rules.ITraitRules;
import net.sf.anathema.character.trait.rules.TraitRules;

public class AttributeCharacterContext implements IAttributeCharacterContext {

  private final ICharacterBasics basics;
  private final TraitGroup[] groups;
  private final TraitRules traitRules;

  public AttributeCharacterContext() {
    this.basics = new ICharacterBasics() {
      @Override
      public boolean isExperienced() {
        return false;
      }
    };
    this.groups = new TraitGroup[] { new TraitGroup("Physical", "Strength", "Dexterity", "Stamina"), //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        new TraitGroup("Social", "Charisma", "Manipulation", "Appearance"), //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        new TraitGroup("Mental", "Perception", "Intelligence", "Wits") }; //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    this.traitRules = new TraitRules();
    traitRules.setMiniumalValue(1);

  }

  @Override
  public ITraitRules getRules() {
    return traitRules;
  }

  @Override
  public ICharacterBasics getBasics() {
    return basics;
  }

  @Override
  public TraitGroup[] getTraitGroups() {
    return groups;
  }
}