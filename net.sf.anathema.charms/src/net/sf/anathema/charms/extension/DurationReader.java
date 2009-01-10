package net.sf.anathema.charms.extension;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.charms.data.duration.AmountDto;
import net.sf.anathema.charms.data.duration.DurationDto;
import net.sf.anathema.charms.data.duration.TraitDto;

public class DurationReader {

  public DurationDto read(IExtensionElement durationElement) {
    DurationDto dto = new DurationDto();
    IExtensionElement element = durationElement.getElements()[0];
    dto.until = readUntilDuration(element);
    dto.keyword = readKeywordDuration(element);
    dto.amount = readAmountDuration(element);
    dto.trait = readTraitDuration(element);
    return dto;
  }

  private TraitDto readTraitDuration(IExtensionElement element) {
    TraitDto traitDto = new TraitDto();
    if (!element.getName().equals("trait")) {
      return null;
    }
    traitDto.trait = element.getAttribute("trait");
    traitDto.unit = element.getAttribute("unit");
    return traitDto;
  }

  private AmountDto readAmountDuration(IExtensionElement element) {
    AmountDto amountDto = new AmountDto();
    if (!element.getName().equals("amount")) {
      return null;
    }
    amountDto.amount = element.getIntegerAttribute("amount");
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