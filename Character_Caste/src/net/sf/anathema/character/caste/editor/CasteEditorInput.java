package net.sf.anathema.character.caste.editor;

import java.io.IOException;
import java.net.URL;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.caste.model.ICasteModel;
import net.sf.anathema.character.core.model.AbstractCharacterModelEditorInput;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class CasteEditorInput extends AbstractCharacterModelEditorInput<ICasteModel> {

  private final ICasteModel casteModel;

  public CasteEditorInput(IFile file, URL imageUrl, IDisplayNameProvider displayNameProvider, ICasteModel casteModel) {
    super(file, imageUrl, displayNameProvider);
    this.casteModel = casteModel;
  }

  @Override
  public ICasteModel getItem() {
    return casteModel;
  }

  @Override
  public ICasteModel save(IProgressMonitor monitor) throws IOException, CoreException, PersistenceException {
    return null;
  }

  @Override
  protected String getModelId() {
    return ICasteModel.ID;
  }
}