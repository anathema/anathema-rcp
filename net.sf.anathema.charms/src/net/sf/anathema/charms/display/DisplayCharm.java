package net.sf.anathema.charms.display;

import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.SourceDto;
import net.sf.anathema.charms.data.cost.CostDto;
import net.sf.anathema.charms.data.cost.ResourceDto;
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
}