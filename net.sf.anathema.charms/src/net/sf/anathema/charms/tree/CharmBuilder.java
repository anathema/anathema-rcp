package net.sf.anathema.charms.tree;

import java.text.MessageFormat;
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
  private final String treeId;

  public CharmBuilder(String treeId) {
    this.treeId = treeId;
  }

  public void addCharm(IExtensionElement charmElement) {
    String charmId = getCharmId(charmElement, ATTRIB_ID);
    IExtensionElement[] prerequisiteElements = charmElement.getElements();
    if (prerequisiteElements.length == 0) {
      addRootCharm(charmId);
    }
    else {
      addCharmWithPrerequisites(charmId, prerequisiteElements);
    }
  }

  private String getCharmId(IExtensionElement element, String idAttributeName) {
    String idPattern = element.getAttribute(idAttributeName);
    return MessageFormat.format(idPattern, treeId);
  }

  private void addCharmWithPrerequisites(String charmId, IExtensionElement[] prerequisiteElements) {
    for (IExtensionElement prerequisiteElement : prerequisiteElements) {
      String prerequisiteId = getCharmId(prerequisiteElement, ATTRIB_CHARM_ID);
      prerequisites.add(new CharmPrerequisite(prerequisiteId, charmId));
      explicitCharms.add(charmId);
      implicitCharms.add(prerequisiteId);
    }
  }

  private void addRootCharm(String charmId) {
    prerequisites.add(new CharmPrerequisite(null, charmId));
    explicitCharms.add(charmId);
  }

  public CharmPrerequisite[] create() {
    implicitCharms.removeAll(explicitCharms);
    for (String implicit : implicitCharms) {
      prerequisites.add(new CharmPrerequisite(null, implicit));
    }
    return prerequisites.toArray(new CharmPrerequisite[prerequisites.size()]);
  }
}