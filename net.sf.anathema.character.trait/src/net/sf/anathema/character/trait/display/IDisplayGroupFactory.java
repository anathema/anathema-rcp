package net.sf.anathema.character.trait.display;

import java.util.List;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;

import org.eclipse.core.runtime.IExecutableExtension;

public interface IDisplayGroupFactory extends IExecutableExtension{

  List<IDisplayTraitGroup<IDisplayTrait>> createDisplayTraitGroups(ICharacter character);
}