package net.sf.anathema.character.freebies.configuration;

import net.sf.anathema.character.core.model.ICharacterId;

import org.eclipse.core.runtime.IExecutableExtension;

public interface IFreebiesHandler extends IExecutableExtension {

  public int getPoints(ICharacterId id);
}