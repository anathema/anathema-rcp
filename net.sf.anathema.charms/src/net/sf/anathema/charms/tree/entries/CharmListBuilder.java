package net.sf.anathema.charms.tree.entries;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.charms.tree.ICharmId;

public class CharmListBuilder<T> implements ICharmListBuilder<T> {

  private final Set<T> charms = new HashSet<T>();
  private final List<ICharmId> explicitCharms = new ArrayList<ICharmId>();
  private final List<ICharmId> implicitCharms = new ArrayList<ICharmId>();
  private static final String ATTRIB_CHARM_ID = "charmId"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private final ICharmFactory<T> factory;

  public CharmListBuilder(ICharmFactory<T> factory) {
    this.factory = factory;
  }

  public void addCharm(IExtensionElement charmElement) {
    ICharmId charmId = factory.getCharmId(charmElement, ATTRIB_ID);
    IExtensionElement[] prerequisiteElements = charmElement.getElements();
    if (prerequisiteElements.length == 0) {
      addRootCharm(charmId);
    }
    else {
      addCharmWithPrerequisites(charmId, prerequisiteElements);
    }
  }

  private void addCharmWithPrerequisites(ICharmId charmId, IExtensionElement[] prerequisiteElements) {
    for (IExtensionElement prerequisiteElement : prerequisiteElements) {
      ICharmId prerequisiteId = factory.getCharmId(prerequisiteElement, ATTRIB_CHARM_ID);
      charms.add(factory.createPrerequisite(getKnownId(prerequisiteId), getKnownId(charmId)));
      explicitCharms.add(charmId);
      implicitCharms.add(prerequisiteId);
    }
  }

  private ICharmId getKnownId(final ICharmId id) {
    if (explicitCharms.contains(id)) {
      return explicitCharms.get(explicitCharms.indexOf(id));
    }
    if (implicitCharms.contains(id)) {
      return implicitCharms.get(implicitCharms.indexOf(id));
    }
    return id;
  }

  private void addRootCharm(ICharmId charmId) {
    charms.add(factory.createRoot(charmId));
    explicitCharms.add(charmId);
  }

  public List<T> create() {
    implicitCharms.removeAll(explicitCharms);
    for (ICharmId charmId : implicitCharms) {
      charms.add(factory.createRoot(charmId));
    }
    return new ArrayList<T>(charms);
  }
}