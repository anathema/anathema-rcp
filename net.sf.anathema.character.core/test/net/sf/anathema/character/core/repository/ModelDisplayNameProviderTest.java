package net.sf.anathema.character.core.repository;

import static org.junit.Assert.*;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;

import org.junit.Test;

public class ModelDisplayNameProviderTest {

  private final class NullProvider implements IDisplayNameProvider {
    private final String string;

    public NullProvider(String string) {
      this.string = string;
    }

    @Override
    public String getDisplayName() {
      return string;
    }
  }

  @Test
  public void equalsWithEqualProviderAndName() throws Exception {
    IDisplayNameProvider provider = new NullProvider("Name"); //$NON-NLS-1$
    assertEquals(new ModelDisplayNameProvider("Test", provider), new ModelDisplayNameProvider("Test", provider)); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Test
  public void equalsIfResultEquals() throws Exception {
    IDisplayNameProvider provider1 = new NullProvider("Has"); //$NON-NLS-1$
    IDisplayNameProvider provider2 = new NullProvider("Has"); //$NON-NLS-1$
    assertEquals(new ModelDisplayNameProvider("Test", provider1), (new ModelDisplayNameProvider("Test", provider2))); //$NON-NLS-1$ //$NON-NLS-2$
  }
}