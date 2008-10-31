package net.sf.anathema.character.points.configuration.internal;

import net.sf.anathema.character.core.character.ICharacterTemplate;

public interface IPointConfigurationProvider {

  public abstract IPointConfiguration[] getExperiencePointConfigurations(ICharacterTemplate template);

  public abstract IPointConfiguration[] getBonusPointConfigurations(ICharacterTemplate template);
}