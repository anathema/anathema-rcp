package net.sf.anathema.charms.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.charms.data.CharmPrerequisite;

public class CharmBuilder {
  private final Set<CharmPrerequisite> prerequisites = new HashSet<CharmPrerequisite>();
  private final List<ICharmId> explicitCharms = new ArrayList<ICharmId>();
  private final List<ICharmId> implicitCharms = new ArrayList<ICharmId>();
  private static final String ATTRIB_CHARM_ID = "charmId"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private final String primaryTrait;

  public CharmBuilder(String primaryTrait) {
    this.primaryTrait = primaryTrait;
  }

  public void addCharm(IExtensionElement charmElement) {
    ICharmId charmId = getCharmId(charmElement, ATTRIB_ID);
    IExtensionElement[] prerequisiteElements = charmElement.getElements();
    if (prerequisiteElements.length == 0) {
      addRootCharm(charmId);
    }
    else {
      addCharmWithPrerequisites(charmId, prerequisiteElements);
    }
  }

  private ICharmId getCharmId(IExtensionElement element, String idAttributeName) {
    String idPattern = element.getAttribute(idAttributeName);
    return new CharmId(idPattern, primaryTrait);
  }

  private void addCharmWithPrerequisites(ICharmId charmId, IExtensionElement[] prerequisiteElements) {
    for (IExtensionElement prerequisiteElement : prerequisiteElements) {
      ICharmId prerequisiteId = getCharmId(prerequisiteElement, ATTRIB_CHARM_ID);
      prerequisites.add(createPrerequisite(prerequisiteId, charmId));
      explicitCharms.add(charmId);
      implicitCharms.add(prerequisiteId);
    }
  }

  private void addRootCharm(ICharmId charmId) {
    prerequisites.add(createRoot(charmId));
    explicitCharms.add(charmId);
  }

  private CharmPrerequisite createRoot(ICharmId charmId) {
    return createPrerequisite(null, charmId);
  }

  private CharmPrerequisite createPrerequisite(final ICharmId source, ICharmId destination) {
    return new CharmPrerequisite(getKnownId(source), getKnownId(destination));
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

  public CharmPrerequisite[] create() {
    implicitCharms.removeAll(explicitCharms);
    for (ICharmId charmId : implicitCharms) {
      prerequisites.add(createRoot(charmId));
    }
    return prerequisites.toArray(new CharmPrerequisite[prerequisites.size()]);
  }
}