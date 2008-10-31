package net.sf.anathema.character.trait.group;

import org.eclipse.core.commands.common.IIdentifiable;

public interface ITraitGroup extends IIdentifiable {

  public String[] getTraitIds();

  public String getLabel();
}