package net.sf.anathema.character.basics;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.model.IModel;
import net.sf.anathema.character.core.model.IModelFactory;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

public class CharacterBasicsFactory extends AbstractExecutableExtension  implements IModelFactory {

  private final IModelPersister<?> persister = new CharacterBasicsPersister();
  
  @Override
  public IModel create(IFile modelFile) throws PersistenceException, CoreException {
    if (!modelFile.exists()) {
      return persister.createNew();
    }
    Document document = DocumentUtilities.read(modelFile.getContents());
    return persister.load(document);
  }
}