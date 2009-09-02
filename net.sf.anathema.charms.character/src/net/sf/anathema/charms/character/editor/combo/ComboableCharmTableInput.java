package net.sf.anathema.charms.character.editor.combo;

import static net.disy.commons.core.util.CollectionUtilities.filter;

import java.util.List;
import java.util.Set;

import net.disy.commons.core.model.listener.IChangeListener;
import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.charms.character.combo.Combo;
import net.sf.anathema.charms.character.combo.ComboBuilder;
import net.sf.anathema.charms.character.editor.ComboEditorInput;
import net.sf.anathema.charms.character.editor.table.ICharmTableInput;
import net.sf.anathema.charms.character.evaluation.CharmCharacter;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.lib.control.change.ChangeControl;

public final class ComboableCharmTableInput extends ChangeControl implements ICharmTableInput {

  private final IChangeListener changeListener = new IChangeListener() {

    @Override
    public void stateChanged() {
      fireChangedEvent();
    }
  };
  private final IModelContainer modelContainer;
  private final IPredicate<ICharmId> validCharmPredicate = new IPredicate<ICharmId>() {
    
    @Override
    public boolean evaluate(ICharmId charmId) {
      Combo combo = editorInput.getComboEditModel().createCombo();
      ComboBuilder builder = new ComboBuilder(combo, editorInput.getCharmDataMap());
      return builder.isValid(charmId);
    }
  };

  private final ComboEditorInput editorInput;

  public ComboableCharmTableInput(ComboEditorInput editorInput, IModelContainer modelContainer) {
    this.editorInput = editorInput;
    this.modelContainer = modelContainer;
    this.modelContainer.getModel(IExperience.MODEL_ID).addChangeListener(changeListener);
    this.modelContainer.getModel(ICharmModel.MODEL_ID).addChangeListener(changeListener);
    this.editorInput.getComboEditModel().addChangeListener(changeListener);
  }

  @Override
  public ICharmId[] getAllCharms() {
    CharmCharacter charmCharacter = new CharmCharacter(modelContainer);
    Set<ICharmId> allCharms = charmCharacter.getAllLearnedCharms();
    List<ICharmId> validCharms = filter(allCharms, validCharmPredicate);
    return validCharms.toArray(new ICharmId[validCharms.size()]);
  }

  @Override
  public void dispose() {
    this.modelContainer.getModel(IExperience.MODEL_ID).removeChangeListener(changeListener);
    this.modelContainer.getModel(ICharmModel.MODEL_ID).removeChangeListener(changeListener);
    this.editorInput.getComboEditModel().removeChangeListener(changeListener);
  }
}