package net.sf.anathema.charms.display;

import java.text.MessageFormat;

import net.sf.anathema.charms.data.cost.BaseDto;
import net.sf.anathema.charms.data.cost.LinearDto;
import net.sf.anathema.charms.data.cost.ResourceDto;
import net.sf.anathema.charms.view.tooltipp.ConcatenateString;
import net.sf.anathema.charms.view.tooltipp.DisplayResourceType;

public class DisplayResource {

  private final ResourceDto resource;
  private final DisplayResourceType resourceType = new DisplayResourceType();

  public DisplayResource(ResourceDto resource) {
    this.resource = resource;
  }

  public String get() {
    String shortType = resourceType.getShortName(resource.type);
    ConcatenateString resourceBuilder = new ConcatenateString("+");
    resourceBuilder.concatenate(getBaseAmount(shortType));
    resourceBuilder.concatenate(getLinearAmount(shortType));
    return resourceBuilder.create();
  }

  private String getBaseAmount(String shortType) {
    BaseDto dto = resource.baseDto;
    if (dto == null) {
      return null;
    }
    String message = dto.orMore ? "{0}{1}+" : "{0}{1}";
    return MessageFormat.format(message, dto.amount, shortType);
  }

  private String getLinearAmount(String shortType) {
    LinearDto dto = resource.linearDto;
    if (dto == null) {
      return null;
    }
    return MessageFormat.format("{0}{1}/{2}", dto.amount, shortType, dto.unit);
  }
}