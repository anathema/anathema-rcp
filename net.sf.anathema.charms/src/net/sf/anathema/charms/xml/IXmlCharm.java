package net.sf.anathema.charms.xml;

import java.util.Set;

import net.sf.anathema.charms.data.CharmPrerequisite;

public interface IXmlCharm {

  public String getTreePart();

  public void addPrerequisites(Set<CharmPrerequisite> prerequisites);
}