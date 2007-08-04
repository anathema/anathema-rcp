package net.sf.anathema.character.points;

import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.character.core.editors.ModelIdentifierProvider;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.experience.model.IExperience;
import net.sf.anathema.lib.control.change.IChangeListener;

import org.eclipse.ui.IEditorInput;

public final class ExperienceUpdatable implements IUpdatable, IExperienceProvider {
  private final IPartContainer partContainer;
  private IExperience experience = null;
  private final IModelProvider modelProvider;
  private final IUpdatable modelChangeUpdatable;
  private IChangeListener modelChangedListener = new IChangeListener() {
    @Override
    public void changeOccured() {
      modelChangeUpdatable.update();
    }
  };

  public ExperienceUpdatable(IPartContainer partContainer, IUpdatable modelChangeUpdatable) {
    this(partContainer, modelChangeUpdatable, ModelCache.getInstance());
  }

  public ExperienceUpdatable(IPartContainer partContainer, IUpdatable modelChangeUpdatable, IModelProvider modelProvider) {
    this.partContainer = partContainer;
    this.modelChangeUpdatable = modelChangeUpdatable;
    this.modelProvider = modelProvider;
    update();
  }

  @Override
  public void update() {
    if (experience != null) {
      experience.removeChangeListener(modelChangedListener);
    }
    IEditorInput editorInput = partContainer.getEditorInput();
    IModelIdentifier modelIdentifier = new ModelIdentifierProvider().getModelIdentifier(editorInput);
    if (modelIdentifier == null) {
      experience = null;
    }
    else {
      ICharacterId characterId = modelIdentifier.getCharacterId();
      experience = (IExperience) modelProvider.getModel(new ModelIdentifier(characterId, IExperience.MODEL_ID));
      experience.addChangeListener(modelChangedListener);
    }
  }

  public IExperience getExperience() {
    return experience;
  }
}