package net.sf.anathema.character.attributes.points;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;

public class TertiaryAttributeFreebies extends AbstractExecutableExtension implements IFreebiesHandler {
  private static final String CREDIT_ID = "net.sf.anathema.character.attributes.freebies.tertiary"; //$NON-NLS-1$
  private final PrioritylessAttributeFreebies prioritylessAttributeFreebies;
  
  public TertiaryAttributeFreebies() {
    this.prioritylessAttributeFreebies = new PrioritylessAttributeFreebies();
  }
  
  public TertiaryAttributeFreebies(IModelProvider modelProvider) {
    this.prioritylessAttributeFreebies = new PrioritylessAttributeFreebies(modelProvider);
  }

  @Override
  public int getPoints(ICharacterId id, int credit) {
    return prioritylessAttributeFreebies.getPoints(id, AttributePointCalculator.TERTIARY, credit);
  }

  @Override
  public String getCreditId() {
    return CREDIT_ID;
  }
}