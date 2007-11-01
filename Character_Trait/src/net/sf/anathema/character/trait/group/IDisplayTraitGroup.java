package net.sf.anathema.character.trait.group;

import net.sf.anathema.character.trait.display.IDisplayTrait;

import org.eclipse.core.commands.common.IIdentifiable;

public interface IDisplayTraitGroup<T extends IDisplayTrait> extends IIdentifiable {

  public Iterable<T> getTraits();
}