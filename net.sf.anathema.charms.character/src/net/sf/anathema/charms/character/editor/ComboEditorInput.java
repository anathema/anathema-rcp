package net.sf.anathema.charms.character.editor;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.core.model.AbstractCharacterModelEditorInput;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.charms.character.combo.Combo;
import net.sf.anathema.charms.character.combo.ComboPersister;
import net.sf.anathema.charms.character.combo.IComboModel;
import net.sf.anathema.charms.character.editor.combo.ComboableCharmTableInput;
import net.sf.anathema.charms.character.editor.combo.ComboedCharmTableInput;
import net.sf.anathema.charms.character.editor.table.ICharmTableInput;
import net.sf.anathema.charms.data.CachingCharmDataMap;
import net.sf.anathema.charms.data.ICharmDataMap;
import net.sf.anathema.charms.data.INameMap;
import net.sf.anathema.charms.data.lookup.CharmNamesExtensionPoint;
import net.sf.anathema.charms.extension.CharmProvidingExtensionPoint;

import org.eclipse.core.resources.IFile;

public class ComboEditorInput extends AbstractCharacterModelEditorInput<IComboModel> {

  private final IModelContainer modelContainer;
  private final ComboEditModel comboEditModel = new ComboEditModel();
  private final ICharmTableInput comboedCharmTableInput = new ComboedCharmTableInput(comboEditModel);
  private final ICharmTableInput comboableCharmTableInput;
  private final ICharmDataMap charmDataMap;

  public ComboEditorInput(
      IFile modelFile,
      URL imageUrl,
      IModelContainer modelContainer,
      IDisplayNameProvider nameProvider) {
    super(modelFile, imageUrl, nameProvider, new ComboPersister());
    this.modelContainer = modelContainer;
    this.comboableCharmTableInput = new ComboableCharmTableInput(this, modelContainer);
    this.charmDataMap = new CachingCharmDataMap(CharmProvidingExtensionPoint.CreateCharmDataMap());
  }

  @Override
  protected String getModelId() {
    return IComboModel.MODEL_ID;
  }

  @Override
  public IComboModel getItem() {
    return (IComboModel) modelContainer.getModel(IComboModel.MODEL_ID);
  }

  public ICharmTableInput getComboableCharms() {
    return comboableCharmTableInput;
  }

  public ICharmTableInput getComboedCharms() {
    return comboedCharmTableInput;
  }

  public INameMap getCharmNameMap() {
    return new CharmNamesExtensionPoint();
  }

  public ICharmDataMap getCharmDataMap() {
    return charmDataMap;
  }

  public ComboEditModel getComboEditModel() {
    return comboEditModel;
  }

  public void learnCombo() {
    Combo combo = comboEditModel.createComboAndClear();
    if (isExperienced()) {
      getItem().addExperienceLearned(combo);
    }
    else {
      getItem().addCreationLearned(combo);
    }
  }

  public Iterable<Combo> getAllCombos() {
    List<Combo> combos = new ArrayList<Combo>();
    addAll(combos, getItem().getCreationLearned());
    if (isExperienced()) {
      addAll(combos, getItem().getExperienceLearned());
    }
    return combos;
  }

  private void addAll(List<Combo> combos, Iterable<Combo> addition) {
    for (Combo combo : addition) {
      combos.add(combo);
    }
  }

  private boolean isExperienced() {
    IExperience experience = (IExperience) modelContainer.getModel(IExperience.MODEL_ID);
    return experience.isExperienced();
  }
}