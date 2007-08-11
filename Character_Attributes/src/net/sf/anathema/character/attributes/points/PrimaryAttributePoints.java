package net.sf.anathema.character.attributes.points;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;

public class PrimaryAttributePoints extends AbstractExecutableExtension implements IFreebiesHandler {

  @Override
  public String getPoints(ICharacterId id) {
    return "Tum";
  }
}