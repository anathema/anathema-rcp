package net.sf.anathema.charms.display;

import java.text.MessageFormat;

import net.sf.anathema.charms.data.cost.BaseDto;
import net.sf.anathema.charms.data.cost.LinearDto;
import net.sf.anathema.charms.data.cost.ResourceDto;
import net.sf.anathema.charms.view.tooltipp.ConcatenateString;
import net.sf.anathema.charms.view.tooltipp.ResourceMessages;

public class DisplayResource {

  private final ResourceDto resource;

  public DisplayResource(ResourceDto resource) {
    this.resource = resource;
  }

  public String get() {
    String shortType = ResourceMessages.get(resource.type);
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
    StringBuilder builder = new StringBuilder(Messages.DisplayResource_BaseAmountPattern);
    if (dto.orMore) {
      builder.append("+");
    }
    return MessageFormat.format(builder.toString(), dto.amount, shortType);
  }

  private String getLinearAmount(String shortType) {
    LinearDto dto = resource.linearDto;
    if (dto == null) {
      return null;
    }
    return MessageFormat.format(Messages.DisplayResource_LinearAmountPattern, dto.amount, shortType, dto.unit);
  }
}