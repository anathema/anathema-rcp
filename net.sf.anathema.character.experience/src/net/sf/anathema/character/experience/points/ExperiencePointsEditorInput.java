package net.sf.anathema.character.experience.points;

import java.net.URL;

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
}