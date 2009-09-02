package net.sf.anathema.charms.character.editor.combo;

import java.util.Set;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.charms.character.editor.table.ICharmTableInput;
import net.sf.anathema.charms.character.evaluation.CharmCharacter;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.lib.control.change.ChangeControl;

public final class ComboableCharmTableInput extends ChangeControl implements ICharmTableInput {

  private final IModelContainer modelContainer;
  private final IChangeListener modelChangeListener = new IChangeListener() {

    @Override
    public void stateChanged() {
      fireChangedEvent();
    }
  };

  public ComboableCharmTableInput(IModelContainer modelContainer) {
    this.modelContainer = modelContainer;
    this.modelContainer.getModel(IExperience.MODEL_ID).addChangeListener(modelChangeListener);
    this.modelContainer.getModel(ICharmModel.MODEL_ID).addChangeListener(modelChangeListener);
  }

  @Override
  public ICharmId[] getAllCharms() {
    CharmCharacter charmCharacter = new CharmCharacter(modelContainer);
    Set<ICharmId> allCharms = charmCharacter.getAllLearnedCharms();
    return allCharms.toArray(new ICharmId[allCharms.size()]);
  }
  
  @Override
  public void dispose() {
    this.modelContainer.getModel(IExperience.MODEL_ID).removeChangeListener(modelChangeListener);
    this.modelContainer.getModel(ICharmModel.MODEL_ID).removeChangeListener(modelChangeListener);
  }
}