package net.sf.anathema.charms.extension;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.charms.data.duration.AmountDto;
import net.sf.anathema.charms.data.duration.DurationDto;

public class DurationReader {

  public DurationDto read(IExtensionElement durationElement) {
    DurationDto dto = new DurationDto();
    IExtensionElement element = durationElement.getElements()[0];
    dto.until = readUntilDuration(element);
    dto.keyword = readKeywordDuration(element);
    dto.amount = readAmountDuration(element);
    return dto;
  }

  private AmountDto readAmountDuration(IExtensionElement element) {
    AmountDto amountDto = new AmountDto();
    if (element.getName().equals("amount")) {
      amountDto.value = element.getAttribute("value");
    }
    if (element.getName().equals("trait")) {
      amountDto.trait = element.getAttribute("trait");
    }
    amountDto.unit = element.getAttribute("unit");
    return amountDto;
  }

  private String readKeywordDuration(IExtensionElement element) {
    return element.getAttribute("keyword");
  }

  private String readUntilDuration(IExtensionElement element) {
    return element.getAttribute("event");
  }
}