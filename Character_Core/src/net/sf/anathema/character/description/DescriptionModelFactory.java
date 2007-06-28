package net.sf.anathema.character.description;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.model.IModel;
import net.sf.anathema.character.core.model.IModelFactory;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

public class DescriptionModelFactory extends AbstractExecutableExtension implements IModelFactory {

  private final CharacterDescriptionPersister persister = new CharacterDescriptionPersister();
  
  @Override
  public IModel create(IFile modelFile) throws PersistenceException, CoreException {
    return persister.load(DocumentUtilities.read(modelFile.getContents()));
  }
}