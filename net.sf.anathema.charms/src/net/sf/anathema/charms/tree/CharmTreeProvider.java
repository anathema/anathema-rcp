package net.sf.anathema.charms.tree;

public class CharmTreeProvider {

  public static ITreeProvider Create() {
    return new CharmTreeExtensionPoint();
  }
}