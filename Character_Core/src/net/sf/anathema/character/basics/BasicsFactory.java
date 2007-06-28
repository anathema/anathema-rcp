package net.sf.anathema.character.basics;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.model.IModel;
import net.sf.anathema.character.core.model.IModelFactory;

import org.eclipse.core.resources.IFile;

public class BasicsFactory extends AbstractExecutableExtension  implements IModelFactory {

  @Override
  public IModel create(IFile modelFile) {
    return new CharacterBasics();
  }
}