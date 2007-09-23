package net.sf.anathema.character.freebies.attributes;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.freebies.configuration.ICreditProvider;

public class FavoredCountCreditProvider extends AbstractExecutableExtension implements ICreditProvider {

  @Override
  public int getCredit(String templateId) {
    return 2;
  }
}