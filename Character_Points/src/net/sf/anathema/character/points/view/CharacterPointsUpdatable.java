package net.sf.anathema.character.points.view;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.character.core.editors.ModelIdentifierProvider;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.lib.ui.IUpdatable;

import org.eclipse.ui.IEditorInput;

public final class CharacterPointsUpdatable implements IUpdatable, IExperienceProvider {
  private final IPartContainer partContainer;
  private IExperience experience = null;
  private final IModelProvider modelProvider;
  private final IUpdatable modelChangeUpdatable;
  private IChangeListener modelChangedListener = new IChangeListener() {
    @Override
    public void stateChanged() {
      modelChangeUpdatable.update();
    }
  };

  public CharacterPointsUpdatable(IPartContainer partContainer, IUpdatable modelChangeUpdatable, IModelProvider modelProvider) {
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