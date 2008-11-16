package charactertype.solar.acceptance.charms;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.character.tree.CharmTraitLookup;

import org.junit.Test;

public class CharmTraitLookup_Test {

  @Test
  public void providesStealthAsTraitIdForStealthCharm() throws Exception {
    CharmTraitLookup traitProvider = new CharmTraitLookup();
    String traitId = traitProvider.getTraitId("Solar: Invisible Statue Spirit"); //$NON-NLS-1$
    assertThat(traitId, is("Stealth")); //$NON-NLS-1$
  }

  @Test
  public void providesThrownAsTraitIdForThrownCharm() throws Exception {
    CharmTraitLookup traitProvider = new CharmTraitLookup();
    String traitId = traitProvider.getTraitId("Solar: Cascade of Cutting Terror"); //$NON-NLS-1$
    assertThat(traitId, is("Thrown")); //$NON-NLS-1$
  }
}