package net.sf.anathema.character.abilities.util;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.trait.display.IDisplayGroupFactory;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;

public class AbilitiesDisplayGroupFactory extends AbstractExecutableExtension implements IDisplayGroupFactory{

  public List<IDisplayTraitGroup<IDisplayTrait>> createDisplayTraitGroups(ICharacter character) {
    return new DisplayGroupFactory(character, IAbilitiesPluginConstants.MODEL_ID).create();
   }
}