package net.sf.anathema.charms.character;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.model.IConfigurableViewElement;
import net.sf.anathema.character.core.model.IViewElementConfigurator;

public class TreeSelectionConfigurator extends AbstractExecutableExtension implements IViewElementConfigurator {

  @Override
  public void configure(IConfigurableViewElement modelViewElement) {
    System.err.println("Hier werden die Kinder eingefügt");
  }
}
