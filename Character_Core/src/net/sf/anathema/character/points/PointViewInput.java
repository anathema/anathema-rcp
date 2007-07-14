package net.sf.anathema.character.points;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.core.model.internal.IPointConfiguration;

import org.eclipse.core.resources.IFolder;

public class PointViewInput implements IPointViewInput {

  private final IFolder folder;
  private final IPointConfiguration[] pointConfigurations;

  public PointViewInput(IFolder folder, IPointConfiguration[] pointConfigurations) {
    this.folder = folder;
    this.pointConfigurations = pointConfigurations;
  }

  @Override
  public IFolder getFolder() {
    return folder;
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
                return input.getPoints(folder);
              }
            };
          }
        });
  }
}