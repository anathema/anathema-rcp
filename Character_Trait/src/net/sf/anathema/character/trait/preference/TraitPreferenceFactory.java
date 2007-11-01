package net.sf.anathema.character.trait.preference;

import net.sf.anathema.character.trait.plugin.CharacterTraitPlugin;
import net.sf.anathema.character.trait.preference.internal.TraitPreferences;

public class TraitPreferenceFactory {

  public static ITraitPreferences create() {
    return new TraitPreferences(CharacterTraitPlugin.getDefaultInstance().getPreferenceStore());
  }
}