package net.sf.anathema.character.points.view;

import java.util.List;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.points.configuration.internal.IPointConfiguration;
import net.sf.anathema.character.points.view.entry.PointConfigurationTransformer;
import net.sf.anathema.lib.collection.CollectionUtilities;
import net.sf.anathema.view.valuelist.IValueEntry;

public class PointsValueEntryFactory implements ICharacterValueEntryFactory {

  private final ICharacterId characterId;
  private final Iterable<IPointConfiguration> pointConfigurations;

  public PointsValueEntryFactory(ICharacterId characterId, Iterable<IPointConfiguration> pointConfigurations) {
    this.characterId = characterId;
    this.pointConfigurations = pointConfigurations;
  }

  @Override
  public ICharacterId getCharacterId() {
    return characterId;
  }

  public List<IValueEntry> createEntries() {
    PointConfigurationTransformer transformer = new PointConfigurationTransformer(characterId);
    return CollectionUtilities.transform(pointConfigurations, transformer);
  }
}