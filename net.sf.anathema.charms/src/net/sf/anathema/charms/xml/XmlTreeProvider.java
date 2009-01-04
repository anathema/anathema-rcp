package net.sf.anathema.charms.xml;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.sf.anathema.charms.data.CharmPrerequisite;
import net.sf.anathema.charms.tree.ITreeProvider;
import net.sf.anathema.charms.tree.TreeDto;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class XmlTreeProvider implements ITreeProvider {

  private static final String ATTRIB_RESOURCE = "resource"; //$NON-NLS-1$
  private IXmlCharmCollection charmCollection;

  @Override
  public CharmPrerequisite[] getTree(String id) {
    Set<CharmPrerequisite> prerequisites = new LinkedHashSet<CharmPrerequisite>();
    for (IXmlCharm charm : charmCollection) {
      if (id.equals(charm.getTreePart())) {
        charm.addPrerequisites(prerequisites);
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
    setCharmCollection(new XmlCharmCollection(config.getAttribute(ATTRIB_RESOURCE), config.getContributor()));
  }

  protected void setCharmCollection(IXmlCharmCollection newCollection) {
    this.charmCollection = newCollection;
  }

  @Override
  public List<String> getGenericCharms(String typeId) {
    // TODO: Generic Charms
    return new ArrayList<String>();
  }
}