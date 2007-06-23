package net.sf.anathema.character.attributes;

import net.sf.anathema.character.trait.IDisplayTrait;

import org.eclipse.core.commands.common.IIdentifiable;

public interface ITraitGroup extends IIdentifiable {

  public Iterable<IDisplayTrait> getTraits();

}
