package net.sf.anathema.character.trait.preference;

import net.sf.anathema.character.trait.plugin.CharacterTraitPlugin;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class TraitPreferenceInitializer extends AbstractPreferenceInitializer {

  @Override
  public void initializeDefaultPreferences() {
    IPreferenceStore preferenceStore = CharacterTraitPlugin.getDefaultInstance().getPreferenceStore();
    new TraitPreferences(preferenceStore).initializeDefaults();
  }
}