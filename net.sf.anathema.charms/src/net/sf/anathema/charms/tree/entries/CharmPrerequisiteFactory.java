package net.sf.anathema.charms.tree.entries;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.charms.data.CharmPrerequisite;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;

public class CharmPrerequisiteFactory implements ICharmFactory<CharmPrerequisite> {
  private final String primaryTrait;

  public CharmPrerequisiteFactory(String primaryTrait) {
    this.primaryTrait = primaryTrait;
  }

  public ICharmId getCharmId(IExtensionElement element, String idAttributeName) {
    String idPattern = element.getAttribute(idAttributeName);
    return new CharmId(idPattern, primaryTrait);
  }

  public CharmPrerequisite createRoot(ICharmId charmId) {
    return createPrerequisite(null, charmId);
  }

  public CharmPrerequisite createPrerequisite(final ICharmId source, ICharmId destination) {
    return new CharmPrerequisite(source, destination);
  }
}