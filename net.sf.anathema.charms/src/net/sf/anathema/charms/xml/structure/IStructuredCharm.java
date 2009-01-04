package net.sf.anathema.charms.xml.structure;

import java.util.Set;

import net.sf.anathema.charms.data.CharmPrerequisite;

public interface IStructuredCharm {

  public String getTreePart();

  public void addPrerequisites(Set<CharmPrerequisite> prerequisites);
}