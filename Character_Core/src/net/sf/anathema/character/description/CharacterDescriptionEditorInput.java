package net.sf.anathema.character.description;

import java.io.IOException;

import net.sf.anathema.basics.jface.FileEditorInput;
import net.sf.anathema.basics.repository.input.IFileItemEditorInput;
import net.sf.anathema.basics.repository.input.ItemFileWriter;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.osgi.util.NLS;

public class CharacterDescriptionEditorInput extends FileEditorInput implements
    IFileItemEditorInput<ICharacterDescription> {

  private ICharacterDescription item;
  private final CharacterDescriptionPersister persister = new CharacterDescriptionPersister();

  public CharacterDescriptionEditorInput(IFile file, ImageDescriptor imageDescriptor, ICharacterDescription description) {
    super(file, imageDescriptor);
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
}