package net.sf.anathema.character.points;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.internal.IPointConfiguration;

public class PointViewInput implements IPointViewInput {

  private final ICharacterId characterId;
  private final IPointConfiguration[] pointConfigurations;

  public PointViewInput(ICharacterId characterId, IPointConfiguration[] pointConfigurations) {
    this.characterId = characterId;
    this.pointConfigurations = pointConfigurations;
  }

  @Override
  public ICharacterId getCharacterId() {
    return characterId;
  }

  public IPointEntry[] createEntries() {
    return ArrayUtilities.transform(
        pointConfigurations,
        IPointEntry.class,
        new ITransformer<IPointConfiguration, IPointEntry>() {
          @Override
          public IPointEntry transform(final IPointConfiguration input) {
            return new IPointEntry() {
              @Override
              public String getModelDisplayName() {
                return input.getName();
              }

              @Override
              public String getExperiencePoints() {
                return input.getPoints(characterId);
              }
            };
          }
        });
  }
}