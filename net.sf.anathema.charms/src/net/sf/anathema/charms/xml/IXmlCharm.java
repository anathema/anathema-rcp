package net.sf.anathema.charms.xml;

import java.util.Set;

import net.sf.anathema.charms.data.CharmPrerequisite;
import net.sf.anathema.charms.tree.TreeDto;

public interface IXmlCharm {

  public String getTreePart();

  public TreeDto getTreeDto();

  public void addPrerequisites(Set<CharmPrerequisite> prerequisites);

}