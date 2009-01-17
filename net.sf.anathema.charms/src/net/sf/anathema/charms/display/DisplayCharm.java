package net.sf.anathema.charms.display;

import net.sf.anathema.character.trait.resources.TraitMessages;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.SourceDto;
import net.sf.anathema.charms.data.cost.CostDto;
import net.sf.anathema.charms.data.cost.ResourceDto;
import net.sf.anathema.charms.data.duration.DurationDto;
import net.sf.anathema.charms.data.duration.PrimitiveDurationDto;
import net.sf.anathema.charms.view.tooltipp.ConcatenateString;

public class DisplayCharm {

  private final CharmDto data;
  private final String notAvailable;
  private final String none;

  public DisplayCharm(CharmDto data) {
    this.data = data;
    this.notAvailable = "N/A";
    this.none = "-";
  }

  public String getType() {
    String definedType = data.type;
    return definedType == null ? notAvailable : definedType;
  }

  public String getKeywords() {
    ConcatenateString keywordsBuilder = ConcatenateString.CommaSeparated();
    for (String keyword : data.keywords) {
      keywordsBuilder.concatenate(keyword);
    }
    return keywordsBuilder.create(none);
  }

  public String getAllSources() {
    ConcatenateString sourcesBuilder = ConcatenateString.LineWrapping();
    for (SourceDto source : data.sources) {
      sourcesBuilder.concatenate(getSingleSource(source));
    }
    return sourcesBuilder.create(none);
  }

  private String getSingleSource(SourceDto source) {
    ConcatenateString sourceBuilder = ConcatenateString.CommaSeparated();
    sourceBuilder.concatenate(source.source);
    if (source.addition != null) {
      sourceBuilder.concatenate(source.addition);
    }
    return sourceBuilder.create();
  }

  public String getCost() {
    ConcatenateString alternativeCosts = new ConcatenateString(" or ");
    for (CostDto cost : data.costs) {
      alternativeCosts.concatenate(getSingleCost(cost));
    }
    return alternativeCosts.create(none);
  }

  private String getSingleCost(CostDto cost) {
    ConcatenateString costBuilder = ConcatenateString.CommaSeparated();
    for (ResourceDto resource : cost.resources) {
      costBuilder.concatenate(getResource(resource));
    }
    return costBuilder.create(none);
  }

  private String getResource(ResourceDto resource) {
    return new DisplayResource(resource).get();
  }

  public String getDuration() {
    ConcatenateString alternativeDuration = new ConcatenateString(" or ");
    for (DurationDto duration : data.durations) {
      alternativeDuration.concatenate(getSingleDuration(duration));
    }
    return alternativeDuration.create(none);
  }

  private String getSingleDuration(DurationDto duration) {
    if (isKeywordDuration(duration)) {
      return duration.keyword;
    }
    else if (isAdditiveDuration(duration)) {
      return createAdditiveText(duration);
    }
    else {
      return createMinimumText(duration);
    }
  }

  private String createAdditiveText(DurationDto duration) {
    ConcatenateString additiveText = new ConcatenateString(" + "); //$NON-NLS-1$
    for (PrimitiveDurationDto dto : duration.additions) {
      additiveText.concatenate(getPrimitiveText(dto));
    }
    return additiveText.create();
  }

  public String getPrimitiveText(PrimitiveDurationDto primitive) {
    if (primitive.until != null) {
      return "Until " + primitive.until;
    }
    if (primitive.amount != null) {
      return primitive.amount.value + " " + primitive.amount.unit;
    }
    else if (primitive.trait != null) {
      StringBuilder builder = new StringBuilder(new TraitMessages().getNameFor(primitive.trait.trait));
      if (primitive.trait.multiplier != 1) {
        builder.append("*");
        builder.append(primitive.trait.multiplier);
      }
      builder.append(" ");
      builder.append(primitive.trait.unit);
      return builder.toString();
    }
    return null;
  }

  private String createMinimumText(DurationDto duration) {
    ConcatenateString minimumText = new ConcatenateString(" or ");
    for (PrimitiveDurationDto dto : duration.minimums) {
      minimumText.concatenate(getPrimitiveText(dto));
    }
    return minimumText.create() + ", whichever is less";
  }

  private boolean isAdditiveDuration(DurationDto duration) {
    return !duration.additions.isEmpty();
  }

  private boolean isKeywordDuration(DurationDto duration) {
    return duration.keyword != null;
  }
}