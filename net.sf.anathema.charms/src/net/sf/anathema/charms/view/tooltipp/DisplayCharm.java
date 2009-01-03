package net.sf.anathema.charms.view.tooltipp;

import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.SourceDto;

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
    StringBuilder keywordsBuilder = createDefaultStringBuilder();
    for (String keyword : data.keywords) {
      keywordsBuilder.add(keyword);
    }
    return keywordsBuilder.create(none);
  }

  public String getAllSources() {
    StringBuilder sourcesBuilder = createLineWrappingStringBuilder();
    for (SourceDto source : data.sources) {
      sourcesBuilder.add(getSingleSource(source));
    }
    return sourcesBuilder.create(none);
  }

  private String getSingleSource(SourceDto source) {
    StringBuilder sourceBuilder = createDefaultStringBuilder();
    sourceBuilder.add(source.source);
    if (source.addition != null) {
      sourceBuilder.add(source.addition);
    }
    return sourceBuilder.create();
  }

  private StringBuilder createDefaultStringBuilder() {
    return new StringBuilder(", ");
  }

  private StringBuilder createLineWrappingStringBuilder() {
    return new StringBuilder("\n   ");
  }
}