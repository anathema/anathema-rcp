package net.sf.anathema.character.freebies.mark;

public interface IModelMarker {

  public String getMarkerId();

  public boolean isActive();

  public String getDescription(String characterName);
}