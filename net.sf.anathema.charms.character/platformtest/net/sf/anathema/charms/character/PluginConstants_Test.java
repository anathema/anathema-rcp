package net.sf.anathema.charms.character;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.character.plugin.CharmCharacterPlugin;

import org.eclipse.core.runtime.Platform;
import org.junit.Test;

public class PluginConstants_Test {

  @Test
  public void modelIsRegisteredForDefinedConstant() throws Exception {
    assertThat(Platform.getBundle(CharmCharacterPlugin.PLUGIN_ID), is(not(nullValue())));
  }
}