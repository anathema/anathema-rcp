package net.sf.anathema.charms.character.preference;

import net.sf.anathema.charms.character.plugin.CharmCharacterPlugin;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class CharmPreferenceInitializer extends AbstractPreferenceInitializer {

  @Override
  public void initializeDefaultPreferences() {
    IPreferenceStore preferenceStore = CharmCharacterPlugin.getDefaultInstance().getPreferenceStore();
    new CharmPreferences(preferenceStore).initializeDefaults();
  }
}