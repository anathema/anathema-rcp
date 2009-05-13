package net.sf.anathema.character.points.view.entry;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.points.configuration.internal.IPointConfiguration;
import net.sf.anathema.view.valuelist.IValueEntry;

public final class PointConfigurationTransformer implements ITransformer<IPointConfiguration, IValueEntry> {

  private final ICharacterId characterId;

  public PointConfigurationTransformer(ICharacterId characterId) {
    this.characterId = characterId;
  }

  @Override
  public IValueEntry transform(final IPointConfiguration pointConfiguration) {
    return new PointConfigurationValueEntry(pointConfiguration, characterId);
  }
}