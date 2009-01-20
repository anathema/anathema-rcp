package charactertype.db.acceptance;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.core.plugin.ICharacterCorePluginConstants;

import org.junit.Test;

public class CasteImage_Test {

  @Test
  public void hasAirImage() throws Exception {
    assertThat(ICharacterCorePluginConstants.IMAGE_REGISTRY.get("Air"), is(not(nullValue()))); //$NON-NLS-1$
  }
  
  @Test
  public void hasEarthImage() throws Exception {
    assertThat(ICharacterCorePluginConstants.IMAGE_REGISTRY.get("Earth"), is(not(nullValue()))); //$NON-NLS-1$
  }
  
  @Test
  public void hasFireImage() throws Exception {
    assertThat(ICharacterCorePluginConstants.IMAGE_REGISTRY.get("Fire"), is(not(nullValue()))); //$NON-NLS-1$
  }
  
  @Test
  public void hasWaterImage() throws Exception {
    assertThat(ICharacterCorePluginConstants.IMAGE_REGISTRY.get("Water"), is(not(nullValue()))); //$NON-NLS-1$
  }
  
  @Test
  public void hasWoodImage() throws Exception {
    assertThat(ICharacterCorePluginConstants.IMAGE_REGISTRY.get("Wood"), is(not(nullValue()))); //$NON-NLS-1$
  }
}