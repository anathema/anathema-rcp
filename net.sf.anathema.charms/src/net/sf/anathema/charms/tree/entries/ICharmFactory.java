package net.sf.anathema.charms.tree.entries;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.charms.tree.ICharmId;

public interface ICharmFactory<T> {

  T createRoot(ICharmId charmId);

  T createPrerequisite(ICharmId prerequisiteId, ICharmId charmId);

  ICharmId getCharmId(IExtensionElement charmElement, String attributeId);

}
