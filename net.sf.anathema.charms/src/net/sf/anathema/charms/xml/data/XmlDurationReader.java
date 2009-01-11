package net.sf.anathema.charms.xml.data;

import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.duration.AmountDto;
import net.sf.anathema.charms.data.duration.DurationDto;

import org.dom4j.Element;

public class XmlDurationReader {

  private final Element durationElement;
  private final CharmDto dto;

  public XmlDurationReader(Element durationElement, CharmDto dto) {
    this.durationElement = durationElement;
    this.dto = dto;
  }

  public void read() {
    DurationDto duration = new DurationDto();
    duration.keyword = durationElement.attributeValue("duration");
    duration.until = durationElement.attributeValue("event");
    duration.amount = new AmountDto();
    if (durationElement.attribute("amount") != null) {
      duration.amount.value = durationElement.attributeValue("amount");
    }
    duration.amount.trait = durationElement.attributeValue("trait");
    if (durationElement.attribute("multiplier") != null) {
      duration.amount.multiplier = Integer.valueOf(durationElement.attributeValue("multiplier"));
    }
    duration.amount.unit = durationElement.attributeValue("unit");
    dto.durations.add(duration);
  }
}