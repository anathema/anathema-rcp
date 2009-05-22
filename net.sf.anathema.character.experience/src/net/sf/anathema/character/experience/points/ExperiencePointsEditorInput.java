package net.sf.anathema.character.experience.points;

import java.net.URL;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.model.AbstractCharacterModelEditorInput;
import net.sf.anathema.character.experience.IExperiencePoints;

import org.eclipse.core.resources.IFile;

public class ExperiencePointsEditorInput extends AbstractCharacterModelEditorInput<IExperiencePoints> {

  private final IExperiencePoints experiencePointsModel;

  public ExperiencePointsEditorInput(
      IFile file,
      URL imageUrl,
      IDisplayNameProvider displayNameProvider,
      IExperiencePoints experiencePointsModel) {
    super(file, imageUrl, displayNameProvider, new ExperiencePointsPersister());
    this.experiencePointsModel = experiencePointsModel;
  }

  @Override
  public IExperiencePoints getItem() {
    return experiencePointsModel;
  }

  @Override
  protected String getModelId() {
    return IExperiencePoints.MODEL_ID;
  }

  public void addEntry(String text) {
    experiencePointsModel.add(new ExperienceEntryParser().parse(text));
  }

  public void update(ExperienceEntry experienceEntry, String text) {
    if (StringUtilities.isNullOrTrimmedEmpty(text)) {
      experiencePointsModel.delete(experienceEntry);
    }
    else {
      ExperienceEntry updateEntry = new ExperienceEntryParser().parse(text);
      experiencePointsModel.update(experienceEntry, updateEntry);
    }
  }
}