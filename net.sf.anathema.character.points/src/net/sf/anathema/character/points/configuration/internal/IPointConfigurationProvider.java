package net.sf.anathema.character.points.configuration.internal;

import java.util.List;

import net.sf.anathema.character.core.character.ICharacterTemplate;

public interface IPointConfigurationProvider {

  public List<IPointConfiguration> getExperiencePointConfigurations(ICharacterTemplate template);

  public List<IPointConfiguration> getBonusPointConfigurations(ICharacterTemplate template);
}