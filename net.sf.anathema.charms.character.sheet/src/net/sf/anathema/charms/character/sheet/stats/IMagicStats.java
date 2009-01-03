package net.sf.anathema.charms.character.sheet.stats;

public interface IMagicStats extends IStats {

  public String getCostString();

  public String getGroupName();

  public String getType();

  public String getDurationString();

  public String getSourceString();

  public String getDetails();
}