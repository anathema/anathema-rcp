package net.sf.anathema.character.attributes;

import net.sf.anathema.basics.eclipse.plugin.AbstractAnathemaUIPlugin;

public class AttributesPlugin extends AbstractAnathemaUIPlugin {

  public static final String PLUGIN_ID = "net.sf.anathema.character.attributes"; //$NON-NLS-1$
  private static AttributesPlugin instance;

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