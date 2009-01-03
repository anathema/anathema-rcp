package net.sf.anathema.charms.xml;

import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

import net.sf.anathema.character.core.conversion.CharacterTypeConverter;
import net.sf.anathema.charms.data.CharmPrerequisite;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.charms.tree.TreeDto;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class XmlCharm implements IXmlCharm {

  private static final String ATTRIB_EXALT = "exalt";
  private static final String ATTRIB_GROUP = "group";
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String TAG_CHARM_REFERENCE = "charmReference"; //$NON-NLS-1$
  private static final String TAG_PREREQUISITE = "prerequisite"; //$NON-NLS-1$
  private final Element charmElement;
  private TreeDto treeDto;
  private String newCharmType;
  private String group;
  private String newType;

  public XmlCharm(Element charmElement) {
    this.charmElement = charmElement;
  }

  public String getTreePart() {
    return MessageFormat.format("{0}.{1}", getOldCharacterType(), getGroup()).toLowerCase(); //$NON-NLS-1$
  }

  private String getGroup() {
    if (group == null) {
      group = charmElement.attributeValue(ATTRIB_GROUP);
    }
    return group;
  }

  private String getOldCharacterType() {
    if (newCharmType == null) {
      newCharmType = charmElement.attributeValue(ATTRIB_EXALT);
    }
    return newCharmType;
  }

  public TreeDto getTreeDto() {
    if (treeDto == null) {
      treeDto = createDto();
    }
    return treeDto;
  }

  private String getNewCharacterType() {
    if (newType == null) {
      newType = new CharacterTypeConverter().getNewType(getOldCharacterType());
    }
    return newType;
  }

  private String getCharmIdString() {
    return charmElement.attributeValue(ATTRIB_ID);
  }

  private String getPrimaryTrait() {
    return getGroup();
  }

  private TreeDto createDto() {
    TreeDto dto = new TreeDto();
    dto.id = getTreePart();
    dto.name = dto.id;
    dto.characterType = getNewCharacterType();
    dto.primaryTrait = getPrimaryTrait();
    return dto;
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