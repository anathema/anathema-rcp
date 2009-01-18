package net.sf.anathema.charms.xml.structure;

import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

import net.sf.anathema.charms.data.CharmPrerequisite;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.charms.xml.BasicCharm;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class StructuredCharm extends BasicCharm implements IStructuredCharm {

  private static final String ATTRIB_EXALT = "exalt"; //$NON-NLS-1$
  private static final String TAG_CHARM_REFERENCE = "charmReference"; //$NON-NLS-1$
  private static final String TAG_PREREQUISITE = "prerequisite"; //$NON-NLS-1$
  private String newCharmType;

  public StructuredCharm(Element charmElement) {
    super(charmElement);
  }

  public String getTreePart() {
    return MessageFormat.format("{0}.{1}", getOldCharacterType(), getGroup()).toLowerCase(); //$NON-NLS-1$
  }

  private String getOldCharacterType() {
    if (newCharmType == null) {
      newCharmType = charmElement.attributeValue(ATTRIB_EXALT);
    }
    return newCharmType;
  }

  public void addPrerequisites(Set<CharmPrerequisite> prerequisites) {
    // TODO: Umgang mit any excellency
    Element prerequisiteElement = charmElement.element(TAG_PREREQUISITE);
    List<Element> referenceElements = ElementUtilities.elements(prerequisiteElement, TAG_CHARM_REFERENCE);
    if (referenceElements.isEmpty()) {
      prerequisites.add(CharmPrerequisite.ForRoot(getCharmId()));
    }
    else {
      addPrerequisites(prerequisites, referenceElements);
    }
  }

  private void addPrerequisites(Set<CharmPrerequisite> prerequisites, List<Element> referenceElements) {
    for (Element sourceElement : referenceElements) {
      String sourceIdString = sourceElement.attributeValue(ATTRIB_ID);
      ICharmId sourceId = new CharmId(sourceIdString, getPrimaryTrait());
      prerequisites.add(CharmPrerequisite.FromSourceAndDestination(sourceId, getCharmId()));
    }
  }

  private CharmId getCharmId() {
    return new CharmId(getCharmIdString(), getPrimaryTrait());
  }
}