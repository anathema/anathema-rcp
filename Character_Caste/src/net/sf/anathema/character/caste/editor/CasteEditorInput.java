package net.sf.anathema.character.caste.editor;

import java.io.IOException;
import java.net.URL;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.caste.model.Caste;
import net.sf.anathema.character.caste.model.ICaste;
import net.sf.anathema.character.core.model.AbstractCharacterModelEditorInput;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public class CasteEditorInput extends AbstractCharacterModelEditorInput<ICaste> {

  public CasteEditorInput(IFile file, URL imageUrl, IDisplayNameProvider displayNameProvider) {
    super(file, imageUrl, displayNameProvider);
  }

  @Override
  public ICaste getItem() {
    Caste caste = new Caste();
    caste.setClean();
    return caste;
  }

  @Override
  public ICaste save(IProgressMonitor monitor) throws IOException, CoreException, PersistenceException {
    return null;
  }

  @Override
  protected String getModelId() {
    return ICaste.ID;
  }
}