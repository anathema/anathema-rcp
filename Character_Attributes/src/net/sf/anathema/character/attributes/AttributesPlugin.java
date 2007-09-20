package net.sf.anathema.character.attributes;

import java.net.URL;

import net.sf.anathema.basics.eclipse.plugin.AbstractAnathemaUIPlugin;
import net.sf.anathema.basics.eclipse.resource.ResourceUtils;
import net.sf.anathema.character.core.plugin.ICharacterCorePluginConstants;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;

public class AttributesPlugin extends AbstractAnathemaUIPlugin {

  public static final String PLUGIN_ID = "net.sf.anathema.character.attributes"; //$NON-NLS-1$
  private static AttributesPlugin instance;
  public static final String UNSELECTED_BUTTON = "UnselectedButton"; //$NON-NLS-1$
  public static final String SELECTED_BUTTON = "SelectedButton"; //$NON-NLS-1$
  public static final String SURPLUS_BUTTON = "SurplusButton"; //$NON-NLS-1$

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
  protected void initializeImageRegistry(ImageRegistry registry) {
    registry.put(UNSELECTED_BUTTON, createImageDescriptor("BorderUnselectedButton16.png")); //$NON-NLS-1$
    registry.put(SELECTED_BUTTON, createImageDescriptor("BorderSolarButton16.png")); //$NON-NLS-1$
    registry.put(SURPLUS_BUTTON, createImageDescriptor("BorderBonusButton16.png")); //$NON-NLS-1$
  }

  private ImageDescriptor createImageDescriptor(String iconName) {
    URL resourceUrl = ResourceUtils.getResourceUrl(ICharacterCorePluginConstants.PLUGIN_ID, "icons/" + iconName); //$NON-NLS-1$
    return ImageDescriptor.createFromURL(resourceUrl);
  }
}