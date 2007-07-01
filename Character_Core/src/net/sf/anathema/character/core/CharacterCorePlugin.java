package net.sf.anathema.character.core;

import net.sf.anathema.basics.eclipse.plugin.AbstractAnathemaUIPlugin;

public class CharacterCorePlugin extends AbstractAnathemaUIPlugin {

  public static final String PLUGIN_ID = "net.sf.anathema.character.core"; //$NON-NLS-1$
  private static AbstractAnathemaUIPlugin instance;

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