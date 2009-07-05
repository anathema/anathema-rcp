package net.sf.anathema.charms.character.editor;

import java.net.URL;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.model.AbstractCharacterModelEditorInput;
import net.sf.anathema.charms.character.combo.ComboPersister;
import net.sf.anathema.charms.character.combo.IComboModel;

import org.eclipse.core.resources.IFile;

public class ComboEditorInput extends AbstractCharacterModelEditorInput<IComboModel> {

  private final IComboModel combos;

  public ComboEditorInput(IFile modelFile, URL imageUrl, IComboModel combos, IDisplayNameProvider nameProvider) {
    super(modelFile, imageUrl, nameProvider, new ComboPersister());
    this.combos = combos;
  }

  @Override
  protected String getModelId() {
    return IComboModel.MODEL_ID;
  }

  @Override
  public IComboModel getItem() {
    return combos;
  }
}