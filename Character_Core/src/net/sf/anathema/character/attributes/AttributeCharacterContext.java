package net.sf.anathema.character.attributes;

import net.sf.anathema.character.basics.ICharacterBasics;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.rules.ITraitRules;
import net.sf.anathema.character.trait.rules.TraitRules;

import org.eclipse.core.resources.IFolder;

public class AttributeCharacterContext implements IAttributeCharacterContext {

  private final TraitGroup[] groups;
  private final TraitRules traitRules;
  private final IFolder characterFolder;

  public AttributeCharacterContext(IFolder characterFolder) {
    this.characterFolder = characterFolder;
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
    return (ICharacterBasics) ModelCache.getInstance().getModel(
        new ModelIdentifier(characterFolder, ICharacterBasics.MODEL_ID));
  }

  @Override
  public TraitGroup[] getTraitGroups() {
    return groups;
  }
}