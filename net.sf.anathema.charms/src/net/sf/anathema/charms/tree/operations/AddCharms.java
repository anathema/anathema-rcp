package net.sf.anathema.charms.tree.operations;

import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.IClosure;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.charms.tree.CharmBuilder;

public final class AddCharms implements IClosure<IExtensionElement> {
  private final IPredicate<IExtensionElement> predicate;
  private final CharmBuilder charmBuilder;

  public AddCharms(IPredicate<IExtensionElement> predicate, CharmBuilder charmBuilder) {
    this.predicate = predicate;
    this.charmBuilder = charmBuilder;
  }

  @Override
  public void execute(IExtensionElement element) throws RuntimeException {
    if (!predicate.evaluate(element)) {
      return;
    }
    for (IExtensionElement charmElement : element.getElements()) {
      charmBuilder.addCharm(charmElement);
    }
  }
}