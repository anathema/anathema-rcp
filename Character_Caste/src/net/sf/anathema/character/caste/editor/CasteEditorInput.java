package net.sf.anathema.character.caste.editor;

import java.net.URL;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.caste.model.ICasteModel;
import net.sf.anathema.character.caste.persistence.CasteModelPersister;
import net.sf.anathema.character.core.model.AbstractCharacterModelEditorInput;

import org.eclipse.core.resources.IFile;

public class CasteEditorInput extends AbstractCharacterModelEditorInput<ICasteModel> {

  private final ICasteModel casteModel;

  public CasteEditorInput(IFile file, URL imageUrl, IDisplayNameProvider displayNameProvider, ICasteModel casteModel) {
    super(file, imageUrl, displayNameProvider, new CasteModelPersister());
    this.casteModel = casteModel;
  }

  @Override
  public ICasteModel getItem() {
    return casteModel;
  }

  @Override
  protected String getModelId() {
    return ICasteModel.ID;
  }
}