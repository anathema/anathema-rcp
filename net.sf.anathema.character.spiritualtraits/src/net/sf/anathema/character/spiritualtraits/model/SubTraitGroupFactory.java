package net.sf.anathema.character.spiritualtraits.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.trait.display.IDisplayGroupFactory;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;

public class SubTraitGroupFactory extends UnconfiguredExecutableExtension implements IDisplayGroupFactory {

  private final IDisplayGroupFactory factory;
  private final String subGroupName;

  public SubTraitGroupFactory(final IDisplayGroupFactory factory, final String subGroupName) {
    this.factory = factory;
    this.subGroupName = subGroupName;
  }

  @Override
  public List<IDisplayTraitGroup<IDisplayTrait>> createDisplayTraitGroups(final ICharacter character) {
    final List<IDisplayTraitGroup<IDisplayTrait>> allGroups = factory.createDisplayTraitGroups(character);
    final List<IDisplayTraitGroup<IDisplayTrait>> subGroup = new ArrayList<IDisplayTraitGroup<IDisplayTrait>>();
    for (final IDisplayTraitGroup<IDisplayTrait> group : allGroups) {
      if (group.getId().equals(subGroupName)) {
        subGroup.add(group);
      }
    }
    return subGroup;
  }

  public String getSubGroupName() {
    return subGroupName;
  }
}