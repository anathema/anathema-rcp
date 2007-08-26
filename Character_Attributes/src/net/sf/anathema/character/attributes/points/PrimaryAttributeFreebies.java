package net.sf.anathema.character.attributes.points;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;

public class PrimaryAttributeFreebies extends AbstractExecutableExtension implements IFreebiesHandler {
  private final PrioritylessAttributeFreebies prioritylessAttributeFreebies;
  
  public PrimaryAttributeFreebies() {
    this.prioritylessAttributeFreebies = new PrioritylessAttributeFreebies();
  }
  
  public PrimaryAttributeFreebies(IModelProvider modelProvider) {
    this.prioritylessAttributeFreebies = new PrioritylessAttributeFreebies(modelProvider);
  }
  
  @Override
  public int getPoints(ICharacterId id, int credit) {
    return prioritylessAttributeFreebies.getPoints(id, AttributePointCalculator.PRIMARY, credit);
  }
}