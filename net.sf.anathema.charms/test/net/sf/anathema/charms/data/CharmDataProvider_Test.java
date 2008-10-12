package net.sf.anathema.charms.data;

import static org.junit.Assert.*;

import org.junit.Test;

public class CharmDataProvider_Test {

  @Test
  public void returnsNamePartOfIdForUnkownCharm() throws Exception {
    CharmDataProvider provider = new CharmDataProvider();
    assertEquals("Doofmann", provider.getDisplayName("Solar: Doofmann")); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Test
  public void returnsNamePartOfIdForOtherUnkownCharm() throws Exception {
    CharmDataProvider provider = new CharmDataProvider();
    assertEquals("Selber", provider.getDisplayName("Abyssal: Selber")); //$NON-NLS-1$ //$NON-NLS-2$
  }
}