package charactertype.solar.acceptance.charms;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.data.lookup.CharmNamesExtensionPoint;

import org.junit.Test;

public class CharmNameLookup_Test {
  @Test
  public void providesNameForRegisteredCharm() throws Exception {
    CharmNamesExtensionPoint names = new CharmNamesExtensionPoint();
    String name = names.getNameFor("Solar: Invisible Statue Spirit"); //$NON-NLS-1$
    assertThat(name, is("Invisible Statue Spirit")); //$NON-NLS-1$
  }
  
  @Test
  public void providesIdAsNameForUnknownCharm() throws Exception {
    CharmNamesExtensionPoint names = new CharmNamesExtensionPoint();
    String name = names.getNameFor("Solar: Katze"); //$NON-NLS-1$
    assertThat(name, is("Solar: Katze")); //$NON-NLS-1$
  }
}