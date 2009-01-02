package net.sf.anathema.charms.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CharmDataProvider_Test {

  private CharmDataProvider provider;
  private DummyNameMap map;

  @Before
  public void setup() throws Exception {
    map = new DummyNameMap();
    provider = new CharmDataProvider(map);
  }
  
  @Test
  public void looksupNameForIdInMap() throws Exception {
    map.put("Solar: Doofmann", "Doofmann"); //$NON-NLS-1$ //$NON-NLS-2$
    assertEquals("Doofmann", provider.getDisplayName("Solar: Doofmann")); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Test
  public void looksupNameForIdInMapForOtherUnkownCharm() throws Exception {
    map.put("Abyssal: Selber", "Selber"); //$NON-NLS-1$ //$NON-NLS-2$
    assertEquals("Selber", provider.getDisplayName("Abyssal: Selber")); //$NON-NLS-1$ //$NON-NLS-2$
  }
}