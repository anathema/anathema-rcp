package net.sf.anathema.character.attributes;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.model.IModel;
import net.sf.anathema.character.core.model.IModelFactory;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

public class AttributesFactory extends AbstractExecutableExtension implements IModelFactory {

  @Override
  public IModel create(IFile modelFile) throws PersistenceException, CoreException {
    return new AttributesPersister().load(DocumentUtilities.read(modelFile.getContents()));
  }
}