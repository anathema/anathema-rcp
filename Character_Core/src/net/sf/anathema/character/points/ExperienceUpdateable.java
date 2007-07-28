package net.sf.anathema.character.points;

import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.character.core.editors.ModelIdentifierProvider;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.experience.IExperience;

import org.eclipse.ui.IEditorInput;

public final class ExperienceUpdateable implements IUpdateable {
  private final IPartContainer partContainer;
  private IExperience experience = null;
  private final IModelProvider modelProvider;

  public ExperienceUpdateable(IPartContainer partContainer) {
    this(partContainer, ModelCache.getInstance());
  }

  public ExperienceUpdateable(IPartContainer partContainer, IModelProvider modelProvider) {
    this.partContainer = partContainer;
    this.modelProvider = modelProvider;
  }

  @Override
  public void update() {
    IEditorInput editorInput = partContainer.getEditorInput();
    IModelIdentifier modelIdentifier = new ModelIdentifierProvider().getModelIdentifier(editorInput);
    if (modelIdentifier == null) {
      experience = null;
    }
    else {
      ICharacterId characterId = modelIdentifier.getCharacterId();
      experience = (IExperience) modelProvider.getModel(new ModelIdentifier(characterId, IExperience.MODEL_ID));
    }
  }

  public IExperience getExperience() {
    return experience;
  }
}