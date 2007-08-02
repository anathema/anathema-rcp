package net.sf.anathema.character.experience;

import net.sf.anathema.basics.eclipse.plugin.AbstractAnathemaUIPlugin;

public class CharacterExperiencePlugin extends AbstractAnathemaUIPlugin {

  public static final String PLUGIN_ID = "net.sf.anathema.character.experience"; //$NON-NLS-1$
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