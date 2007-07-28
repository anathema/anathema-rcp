package net.sf.anathema.character.core.model.internal;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.template.ICharacterTemplateProvider;

public interface IPointConfigurationProvider {

  public abstract IPointConfiguration[] getExperiencePointConfigurations(
      ICharacterTemplateProvider provider,
      ICharacterId characterId);

  public abstract IPointConfiguration[] getBonusPointConfigurations(
      ICharacterTemplateProvider provider,
      ICharacterId characterId);

}