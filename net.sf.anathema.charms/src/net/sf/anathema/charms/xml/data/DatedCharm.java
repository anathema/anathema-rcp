package net.sf.anathema.charms.xml.data;

import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.xml.BasicCharm;

import org.dom4j.Element;

public class DatedCharm extends BasicCharm implements IDatedCharm {

  public DatedCharm(Element charmElement) {
    super(charmElement);
  }

  @Override
  public CharmDto createDto() {
    CharmDto dto = new CharmDto();
    // TODO: toLowerCase in die Aufbereitung stecken
    dto.type = charmElement.element("charmtype").attributeValue("type").toLowerCase();
    return dto;
  }
}