package net.sf.anathema.charms;

public class CharmPrerequisite {

  private final String source;
  private final String destination;

  public CharmPrerequisite(String source, String destination) {
    this.source = source;
    this.destination = destination;
  }

  public String getSource() {
    return source;
  }

  public String getDestination() {
    return destination;
  }
}