package charactertype.solar.acceptance.charms;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.data.lookup.CharmNamesExtensionPoint;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.DummyCharmId;

import org.junit.Test;

public class CharmNameLookup_Test {
  @Test
  public void providesNameForRegisteredCharm() throws Exception {
    CharmNamesExtensionPoint names = new CharmNamesExtensionPoint();
    String name = names.getNameFor(new CharmId("Solar: Invisible Statue Spirit", "Stealth")); //$NON-NLS-1$ //$NON-NLS-2$
    assertThat(name, is("Invisible Statue Spirit")); //$NON-NLS-1$
  }

  @Test
  public void providesNameWithTraitNameForGenericCharm() throws Exception {
    CharmNamesExtensionPoint names = new CharmNamesExtensionPoint();
    String name = names.getNameFor(new CharmId("solar.any.{0}.excellency", "Strength")); //$NON-NLS-1$ //$NON-NLS-2$
    assertThat(name, is("Any Strength Excellency")); //$NON-NLS-1$
  }

  @Test
  public void providesIdAsNameForUnknownCharm() throws Exception {
    CharmNamesExtensionPoint names = new CharmNamesExtensionPoint();
    String name = names.getNameFor(new DummyCharmId("Solar: Katze")); //$NON-NLS-1$
    assertThat(name, is("Solar: Katze")); //$NON-NLS-1$
  }

}