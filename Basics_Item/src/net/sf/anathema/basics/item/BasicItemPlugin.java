package net.sf.anathema.basics.item;

import net.sf.anathema.basics.eclipse.plugin.AbstractAnathemaUIPlugin;

public class BasicItemPlugin extends AbstractAnathemaUIPlugin {

  public static final String ID = "net.sf.anathema.basic.item"; //$NON-NLS-1$
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