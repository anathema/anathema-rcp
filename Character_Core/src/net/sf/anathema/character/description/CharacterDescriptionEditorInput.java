package net.sf.anathema.character.description;

import java.io.IOException;

import net.sf.anathema.basics.jface.FileEditorInput;
import net.sf.anathema.basics.repository.input.IFileItemEditorInput;
import net.sf.anathema.basics.repository.input.ItemFileWriter;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;

public class CharacterDescriptionEditorInput extends FileEditorInput implements
    IFileItemEditorInput<ICharacterDescription> {

  private ICharacterDescription item;
  private final CharacterDescriptionPersister persister = new CharacterDescriptionPersister();

  public CharacterDescriptionEditorInput(IFile file, ImageDescriptor imageDescriptor)
      throws PersistenceException,
      CoreException {
    super(file, imageDescriptor);
    this.item = persister.load(DocumentUtilities.read(getFile().getContents()));
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
    // TODO Idee für den Namen von Character Description im Editor
    return "Description - " + item.getName().getText();
  }
}