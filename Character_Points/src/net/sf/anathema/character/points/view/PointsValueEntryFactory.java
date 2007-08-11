package net.sf.anathema.character.points.view;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.points.configuration.internal.IPointConfiguration;
import net.sf.anathema.view.valuelist.IValueEntry;

public class PointsValueEntryFactory implements ICharacterValueEntryFactory {

  private final class PointConfigurationValueEntry implements IValueEntry {
    private final IPointConfiguration pointConfiguration;

    private PointConfigurationValueEntry(IPointConfiguration pointConfiguration) {
      this.pointConfiguration = pointConfiguration;
    }

    @Override
    public String getDisplayName() {
      return pointConfiguration.getName();
    }

    @Override
    public String getValue() {
      return pointConfiguration.getPoints(characterId);
    }
  }

  private final ICharacterId characterId;
  private final IPointConfiguration[] pointConfigurations;

  public PointsValueEntryFactory(ICharacterId characterId, IPointConfiguration[] pointConfigurations) {
    this.characterId = characterId;
    this.pointConfigurations = pointConfigurations;
  }

  @Override
  public ICharacterId getCharacterId() {
    return characterId;
  }

  public IValueEntry[] createEntries() {
    return ArrayUtilities.transform(
        pointConfigurations,
        IValueEntry.class,
        new ITransformer<IPointConfiguration, IValueEntry>() {
          @Override
          public IValueEntry transform(final IPointConfiguration pointConfiguration) {
            return new PointConfigurationValueEntry(pointConfiguration);
          }
        });
  }
}