package net.sf.anathema.charms.xml;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.sf.anathema.charms.data.CharmPrerequisite;
import net.sf.anathema.charms.tree.IExecutableTreeProvider;
import net.sf.anathema.charms.tree.TreeDto;
import net.sf.anathema.charms.xml.structure.IStructuredCharm;
import net.sf.anathema.charms.xml.structure.IStructuredCharmCollection;
import net.sf.anathema.charms.xml.structure.StructuredCharmCollection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class XmlTreeProvider implements IExecutableTreeProvider {

  private static final String ATTRIB_RESOURCE = "resource"; //$NON-NLS-1$
  private final List<IStructuredCharmCollection> charmCollections = new ArrayList<IStructuredCharmCollection>();

  @Override
  public CharmPrerequisite[] getTree(String id) {
    Set<CharmPrerequisite> prerequisites = new LinkedHashSet<CharmPrerequisite>();
    for (IStructuredCharmCollection charmCollection : charmCollections) {
      for (IStructuredCharm charm : charmCollection) {
        if (id.equals(charm.getTreePart())) {
          charm.addPrerequisites(prerequisites);
        }
      }
    }
    return prerequisites.toArray(new CharmPrerequisite[prerequisites.size()]);
  }

  @Override
  public List<String> getTreeList() {
    return new ArrayList<String>();
  }

  @Override
  public TreeDto getData(String treeId) {
    return null;
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    for (IConfigurationElement element : config.getChildren()) {
      addCharmCollection(new StructuredCharmCollection(element.getAttribute(ATTRIB_RESOURCE), config.getContributor()));
    }
  }

  protected void addCharmCollection(IStructuredCharmCollection newCollection) {
    charmCollections.add(newCollection);
  }

  @Override
  public List<String> getGenericCharms(String typeId) {
    return new ArrayList<String>();
  }
}