package net.sf.anathema.charms.xml.data;

import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.cost.BaseDto;
import net.sf.anathema.charms.data.cost.CostDto;
import net.sf.anathema.charms.data.cost.LinearDto;
import net.sf.anathema.charms.data.cost.ResourceDto;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class XmlCostReader {

  private static final String ATTRIB_TEXT = "text";
  private final Element costElement;
  private final CostDto costDto;
  private final CharmDto dto;

  public XmlCostReader(Element costElement, CharmDto dto) {
    this.costElement = costElement;
    this.dto = dto;
    this.costDto = new CostDto();
  }

  public void read() throws PersistenceException {
    dto.costs.add(costDto);
    readEssenceCost();
  }

  private void readEssenceCost() throws PersistenceException {
    Element essenceElement = costElement.element("essence");
    if (essenceElement == null) {
      return;
    }
    ResourceDto resource = new ResourceDto();
    resource.type = "motes";
    String textValue = essenceElement.attributeValue(ATTRIB_TEXT);
    if (textValue == null) {
      resource.baseDto = new BaseDto();
      resource.baseDto.amount = ElementUtilities.getRequiredIntAttrib(essenceElement, "cost");
      resource.baseDto.orMore = false;
    }
    else {
      resource.linearDto = new LinearDto();
      resource.linearDto.amount = ElementUtilities.getRequiredIntAttrib(essenceElement, "cost");
      resource.linearDto.unit = textValue.replace(" per ", "");
    }
    costDto.resources.add(resource);
  }
}