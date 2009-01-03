package net.sf.anathema.charms.character.preference;

import net.sf.anathema.charms.character.plugin.CharmCharacterPlugin;

public class CharmPreferenceFactory {

  public static ICharmPreferences create() {
    return new CharmPreferences(CharmCharacterPlugin.getDefaultInstance().getPreferenceStore());
  }
}