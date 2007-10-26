package net.sf.anathema.character.description;

import java.io.IOException;
import java.net.URL;

import net.sf.anathema.basics.repository.input.ItemFileWriter;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.AbstractCharacterModelEditorInput;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.osgi.util.NLS;

public class CharacterDescriptionEditorInput extends AbstractCharacterModelEditorInput<ICharacterDescription> {

  private ICharacterDescription item;
  private final CharacterDescriptionPersister persister = new CharacterDescriptionPersister();

  public CharacterDescriptionEditorInput(IFile file, URL imageUrl, ICharacterDescription description) {
    super(file, imageUrl, null);
    this.item = description;
  }

  @Override
  public ICharacterDescription save(IProgressMonitor monitor) throws IOException, CoreException, PersistenceException {
    new ItemFileWriter().saveToFile(getFile(), persister, item, monitor);
    return item;
  }

  @Override
  public ICharacterDescription getItem() {
    return item;
  }

  public void setItem(ICharacterDescription item) {
    this.item = item;
  }

  @Override
  public String getToolTipText() {
    return getName();
  }

  @Override
  public String getName() {
    return NLS.bind(Messages.CharacterDescriptionEditorInput_Description_Message, item.getName().getText());
  }

  @Override
  protected IModelIdentifier getModelIdentifier() {
    return new ModelIdentifier((IFolder) getFile().getParent(), ICharacterDescription.MODEL_ID);
  }
}