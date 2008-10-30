package net.sf.anathema.character.core.model.change;

import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelIdentifier;

import org.eclipse.core.runtime.IExecutableExtension;

public interface IModelChangeProcessor extends IExecutableExtension {

  public void modelChanged(IModel model, IModelIdentifier identifier);
}