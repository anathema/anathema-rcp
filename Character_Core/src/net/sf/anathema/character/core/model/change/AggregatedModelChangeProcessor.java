package net.sf.anathema.character.core.model.change;

import java.util.ArrayList;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelIdentifier;

public class AggregatedModelChangeProcessor extends AbstractExecutableExtension implements IModelChangeProcessor {

  private final Iterable<IModelChangeProcessor> allProcessors;

  public AggregatedModelChangeProcessor() {
    this.allProcessors = new ArrayList<IModelChangeProcessor>();
  }

  @Override
  public void modelChanged(IModel model, IModelIdentifier identifier) {
    for (IModelChangeProcessor processor : allProcessors) {
      processor.modelChanged(model, identifier);
    }
  }
}