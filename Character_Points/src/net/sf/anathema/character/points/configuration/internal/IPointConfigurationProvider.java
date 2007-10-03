package net.sf.anathema.character.points.configuration.internal;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;

public interface IPointConfigurationProvider {

  public abstract IPointConfiguration[] getExperiencePointConfigurations(
      ICharacterTemplateProvider provider,
      ICharacterId characterId);

  public abstract IPointConfiguration[] getBonusPointConfigurations(
      ICharacterTemplateProvider provider,
      ICharacterId characterId);

}