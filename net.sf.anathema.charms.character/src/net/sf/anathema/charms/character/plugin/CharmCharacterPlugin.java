package net.sf.anathema.charms.character.plugin;

import net.sf.anathema.basics.eclipse.plugin.AbstractAnathemaUIPlugin;

public class CharmCharacterPlugin extends AbstractAnathemaUIPlugin {

  public static final String PLUGIN_ID = "net.sf.anathema.charms.character"; //$NON-NLS-1$
  private static CharmCharacterPlugin instance;

  public static AbstractAnathemaUIPlugin getDefaultInstance() {
    return instance;
  }

  @Override
  protected final void createInstance() {
    instance = this;
  }

  @Override
  protected final void deleteInstance() {
    instance = null;
  }

  @Override
  protected AbstractAnathemaUIPlugin getPluginInstance() {
    return getDefaultInstance();
  }  
}