package net.sf.anathema.character.points.view.entry;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.points.configuration.internal.IPointConfiguration;
import net.sf.anathema.view.valuelist.IValueEntry;

public final class PointConfigurationValueEntry implements IValueEntry {
  private final IPointConfiguration pointConfiguration;
  private final ICharacterId characterId;

  public PointConfigurationValueEntry(IPointConfiguration pointConfiguration, ICharacterId characterId) {
    this.pointConfiguration = pointConfiguration;
    this.characterId = characterId;
  }

  @Override
  public String getDisplayName() {
    return pointConfiguration.getName();
  }

  @Override
  public String getValue() {
    return String.valueOf(pointConfiguration.getPoints(characterId));
  }
}