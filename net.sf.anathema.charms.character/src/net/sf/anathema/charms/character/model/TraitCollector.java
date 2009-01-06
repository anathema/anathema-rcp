package net.sf.anathema.charms.character.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.trait.display.DisplayFactoryLookup;
import net.sf.anathema.character.trait.display.IDisplayGroupFactory;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.model.MainTraitModelProvider;

public class TraitCollector {
  
  private final ICharacter character;

  public TraitCollector(ICharacter character) {
    this.character = character;
  }

  public Collection<IDisplayTrait> getAllTraits() {
    ArrayList<IDisplayTrait> list = new ArrayList<IDisplayTrait>();
    for (IDisplayTraitGroup<IDisplayTrait> group : getDisplayGroups()) {
      for (IDisplayTrait trait : group) {
        list.add(trait);
      }
    }
    return list;
  }

  private List<IDisplayTraitGroup<IDisplayTrait>> getDisplayGroups() {
    String mainModel = new MainTraitModelProvider().getFor(character.getCharacterType().getId());
    IDisplayGroupFactory factory = new DisplayFactoryLookup().getFor(mainModel);
    return factory.createDisplayTraitGroups(character);
  }
}