package charactertype.solar.acceptance.charms;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.tree.CharmTreeExtensionPoint;

import org.junit.Test;

public class CharmTreeLookup_Test {

  @Test
  public void providesSolarStealthAsTreeForSolarStealthCharm() throws Exception {
    CharmTreeExtensionPoint traitProvider = new CharmTreeExtensionPoint();
    String treeId = traitProvider.getTreeId("Solar: Invisible Statue Spirit"); //$NON-NLS-1$
    assertThat(treeId, is("solar.stealth")); //$NON-NLS-1$
  }

  @Test
  public void providesSolarThrownAsTreeForSolarThrownCharm() throws Exception {
    CharmTreeExtensionPoint traitProvider = new CharmTreeExtensionPoint();
    String treeId = traitProvider.getTreeId("Solar: Cascade of Cutting Terror"); //$NON-NLS-1$
    assertThat(treeId, is("solar.thrown")); //$NON-NLS-1$
  }
}