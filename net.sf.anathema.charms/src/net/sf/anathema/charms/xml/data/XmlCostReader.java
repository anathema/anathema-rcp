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

  private static final String ATTIB_COST = "cost";
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
    readCost("experience", "experience");
    readCost("health", "lethal_hl");
  }

  private void readCost(String tagName, String type) throws PersistenceException {
    Element resourceElement = costElement.element(tagName);
    if (resourceElement == null) {
      return;
    }
    ResourceDto resource = new ResourceDto();
    resource.type = type;
    int amount = ElementUtilities.getRequiredIntAttrib(resourceElement, ATTIB_COST);
    XmlCostText costText = new XmlCostText(resourceElement.attributeValue(ATTRIB_TEXT));
    if (costText.isBase()) {
      readBaseCost(resource, amount, costText);
    }
    if (costText.isLinear()) {
      readLinearCost(resource, amount, costText);
    }
    costDto.resources.add(resource);
  }

  private void readLinearCost(ResourceDto resource, int amount, XmlCostText text) {
    resource.linearDto = new LinearDto();
    resource.linearDto.amount = text.getLinearAmout(amount);
    resource.linearDto.unit = text.getLinearUnit();
  }

  private void readBaseCost(ResourceDto resource, int amount, XmlCostText text) {
    resource.baseDto = new BaseDto();
    resource.baseDto.amount = amount;
    resource.baseDto.orMore = text.isOrMore();
  }
}