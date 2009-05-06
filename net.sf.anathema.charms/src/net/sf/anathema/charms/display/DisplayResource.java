package net.sf.anathema.charms.display;

import java.text.MessageFormat;

import net.sf.anathema.charms.data.cost.BaseDto;
import net.sf.anathema.charms.data.cost.LinearDto;
import net.sf.anathema.charms.data.cost.ResourceDto;
import net.sf.anathema.charms.view.tooltipp.ResourceMessages;
import net.sf.anathema.lib.lang.ConcatenateString;

public class DisplayResource {

  private final ResourceDto resource;

  public DisplayResource(ResourceDto resource) {
    this.resource = resource;
  }

  public String get() {
    String shortType = ResourceMessages.get(resource.type);
    ConcatenateString resourceBuilder = new ConcatenateString("+");
    resourceBuilder.concatenate(getBaseAmount(shortType));
    concatenateLinearAmounts(resourceBuilder, shortType);
    return resourceBuilder.create();
  }

  private String getBaseAmount(String shortType) {
    BaseDto dto = resource.baseDto;
    if (dto == null) {
      return ConcatenateString.EMPTY_VALUE;
    }
    StringBuilder builder = new StringBuilder(Messages.DisplayResource_BaseAmountPattern);
    if (dto.orMore) {
      builder.append("+");
    }
    return MessageFormat.format(builder.toString(), dto.amount, shortType);
  }

  private void concatenateLinearAmounts(ConcatenateString resourceBuilder, String shortType) {
    for (LinearDto dto : resource.linearDto) {
      resourceBuilder.concatenate(getLinearAmount(dto,shortType));
    }
  }

  private String getLinearAmount(LinearDto dto, String shortType) {
    return MessageFormat.format(Messages.DisplayResource_LinearAmountPattern, dto.amount, shortType, dto.unit);
  }
}