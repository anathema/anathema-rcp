package net.sf.anathema.character.core;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;

import net.sf.anathema.basics.eclipse.plugin.AbstractAnathemaUIPlugin;
import net.sf.anathema.character.core.traitview.CanvasIntValueDisplay;

public class CharacterCorePlugin extends AbstractAnathemaUIPlugin {

  public static final String PLUGIN_ID = "net.sf.anathema.character.core"; //$NON-NLS-1$
  public static final String UNSELECTED_BUTTON = "UnselectedButton"; //$NON-NLS-1$
  public static final String SELECTED_BUTTON = "SelectedButton"; //$NON-NLS-1$
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

  @Override
  protected void initializeImageRegistry(ImageRegistry reg) {
    reg.put(UNSELECTED_BUTTON, ImageDescriptor.createFromFile(
        CanvasIntValueDisplay.class,
        "BorderUnselectedButton16.png")); //$NON-NLS-1$
    reg.put(SELECTED_BUTTON, ImageDescriptor.createFromFile(CanvasIntValueDisplay.class, "BorderSolarButton16.png")); //$NON-NLS-1$
  }

}