package net.sf.anathema.charms.xml;

import net.sf.anathema.charms.tree.ICharmId;

import org.dom4j.Element;

public class BasicCharm {

  protected final Element charmElement;
  protected static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String ATTRIB_GROUP = "group"; //$NON-NLS-1$
  private String group;
  private String charmId;

  public BasicCharm(Element charmElement) {
    this.charmElement = charmElement;
  }

  protected final String getGroup() {
    if (group == null) {
      group = charmElement.attributeValue(ATTRIB_GROUP);
    }
    return group;
  }

  protected final String getCharmIdString() {
    if (charmId == null) {
      charmId = charmElement.attributeValue(ATTRIB_ID);
    }
    return charmId;
  }

  protected final String getPrimaryTrait() {
    return getGroup();
  }

  public final boolean hasId(ICharmId charmId) {
    return getCharmIdString().equals(charmId.getIdPattern()) && getPrimaryTrait().equals(charmId.getPrimaryTrait());
  }
}