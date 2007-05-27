package net.sf.anathema.campaign.plot.dnd;

public enum RelativeLocation {

  Before, After, On;

  public static RelativeLocation getByOrdinal(int ordinal) {
    for (RelativeLocation location : values()) {
      if (location.ordinal() == ordinal) {
        return location;
      }
    }
   throw new IllegalArgumentException("No RelativeLocation for ordinal " + ordinal); //$NON-NLS-1$
  }
}