package net.sf.anathema.charms.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.charms.data.CharmPrerequisite;

public class CharmBuilder {
  private final Set<CharmPrerequisite> prerequisites = new HashSet<CharmPrerequisite>();
  private final List<String> explicitCharms = new ArrayList<String>();
  private final List<String> implicitCharms = new ArrayList<String>();
  private static final String ATTRIB_CHARM_ID = "charmId"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$

  public void addCharm(IExtensionElement charmElement) {
    String currentCharmId = charmElement.getAttribute(ATTRIB_ID);
    for (IExtensionElement prerequisiteElement : charmElement.getElements()) {
      String prerequisiteId = prerequisiteElement.getAttribute(ATTRIB_CHARM_ID);
      prerequisites.add(new CharmPrerequisite(prerequisiteId, currentCharmId));
      explicitCharms.add(currentCharmId);
      implicitCharms.add(prerequisiteId);
    }
    if (!explicitCharms.contains(currentCharmId)) {
      prerequisites.add(new CharmPrerequisite(null, currentCharmId));
      explicitCharms.add(currentCharmId);
    }
  }

  public CharmPrerequisite[] create() {
    implicitCharms.removeAll(explicitCharms);
    for (String implicit : implicitCharms) {
      prerequisites.add(new CharmPrerequisite(null, implicit));
    }
    return prerequisites.toArray(new CharmPrerequisite[prerequisites.size()]);
  }
}