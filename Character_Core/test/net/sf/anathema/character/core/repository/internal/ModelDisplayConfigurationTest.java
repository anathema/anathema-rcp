package net.sf.anathema.character.core.repository.internal;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ModelDisplayConfigurationTest {

  private ModelDisplayConfiguration configuration;

  @Before
  public void createConfiguration() {
    this.configuration = new ModelDisplayConfiguration(null, "test", null); //$NON-NLS-1$
  }

  @Test
  public void equalsConfigurationWithSameFileName() throws Exception {
    assertEquals(configuration, new ModelDisplayConfiguration(null, "test", null)); //$NON-NLS-1$
  }
  
  @Test
  public void doesNotEqualObject() throws Exception {
    assertFalse(configuration.equals(new Object()));
  }
  
  @Test
  public void doesNotEqualConfigurationWithDifferentFileName() throws Exception {
    assertFalse(configuration.equals( new ModelDisplayConfiguration(null, "different", null))); //$NON-NLS-1$
  }
  
}