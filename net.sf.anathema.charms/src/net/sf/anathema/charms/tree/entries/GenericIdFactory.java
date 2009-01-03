package net.sf.anathema.charms.tree.entries;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;

public class GenericIdFactory implements ICharmFactory<String> {

  @Override
  public String createPrerequisite(ICharmId prerequisiteId, ICharmId charmId) {
    return charmId.getIdPattern();
  }

  @Override
  public String createRoot(ICharmId charmId) {
    return charmId.getIdPattern();
  }

  @Override
  public ICharmId getCharmId(IExtensionElement charmElement, String attributeId) {
    return new CharmId(charmElement.getAttribute(attributeId), null);
  }
}