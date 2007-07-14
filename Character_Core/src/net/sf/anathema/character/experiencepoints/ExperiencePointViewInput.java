package net.sf.anathema.character.experiencepoints;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.core.model.internal.IPointConfiguration;
import net.sf.anathema.character.core.model.internal.ModelExtensionPoint;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;

import org.eclipse.core.resources.IFolder;

public class ExperiencePointViewInput implements IExperiencePointViewInput {

  private final IFolder folder;

  public ExperiencePointViewInput(IFolder folder) {
    this.folder = folder;
  }

  @Override
  public IFolder getFolder() {
    return folder;
  }

  public IExperiencePointEntry[] createEntries() {
    IPointConfiguration[] pointConfigurations = new ModelExtensionPoint().getPointConfigurations(
        new CharacterTemplateProvider(),
        folder);
    return ArrayUtilities.transform(
        pointConfigurations,
        IExperiencePointEntry.class,
        new ITransformer<IPointConfiguration, IExperiencePointEntry>() {
          @Override
          public IExperiencePointEntry transform(final IPointConfiguration input) {
            return new IExperiencePointEntry() {
              @Override
              public String getModelDisplayName() {
                return input.getName();
              }

              @Override
              public String getExperiencePoints() {
                return input.getExperiencePoints(folder);
              }
            };
          }
        });
  }
}