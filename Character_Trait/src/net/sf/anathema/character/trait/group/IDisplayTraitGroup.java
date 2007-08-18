package net.sf.anathema.character.trait.group;

import net.sf.anathema.character.trait.IDisplayTrait;

import org.eclipse.core.commands.common.IIdentifiable;

public interface IDisplayTraitGroup extends IIdentifiable {

  public Iterable<IDisplayTrait> getTraits();
}