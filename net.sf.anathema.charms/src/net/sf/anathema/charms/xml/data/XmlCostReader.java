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
    readCost("essence", "motes");
    readCost("willpower", "willpower");
  }

  private void readCost(String tagName, String type) throws PersistenceException {
    Element essenceElement = costElement.element(tagName);
    if (essenceElement == null) {
      return;
    }
    ResourceDto resource = new ResourceDto();
    resource.type = type;
    String textValue = essenceElement.attributeValue(ATTRIB_TEXT);
    if (textValue == null) {
      readBaseCost(essenceElement, resource);
    }
    else {
      readLinearCost(essenceElement, resource, textValue);
    }
    costDto.resources.add(resource);
  }

  private void readLinearCost(Element essenceElement, ResourceDto resource, String textValue)
      throws PersistenceException {
    resource.linearDto = new LinearDto();
    resource.linearDto.amount = ElementUtilities.getRequiredIntAttrib(essenceElement, "cost");
    resource.linearDto.unit = textValue.replace(" per ", "");
  }

  private void readBaseCost(Element essenceElement, ResourceDto resource) throws PersistenceException {
    resource.baseDto = new BaseDto();
    resource.baseDto.amount = ElementUtilities.getRequiredIntAttrib(essenceElement, "cost");
    resource.baseDto.orMore = false;
  }
}