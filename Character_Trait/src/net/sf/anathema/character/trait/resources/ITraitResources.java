package net.sf.anathema.character.trait.resources;

import java.net.URL;

import net.sf.anathema.basics.eclipse.resource.ResourceUtils;
import net.sf.anathema.character.trait.plugin.ITraitPluginConstants;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;

public interface ITraitResources {

  public static final String UNSELECTED_BUTTON = "UnselectedButton"; //$NON-NLS-1$
  public static final String SELECTED_BUTTON = "SelectedButton"; //$NON-NLS-1$
  public static final String SURPLUS_BUTTON = "SurplusButton"; //$NON-NLS-1$

  public static ImageRegistry IMAGE_REGISTRY = new ImageRegistry() {
    {
      put(UNSELECTED_BUTTON, createImageDescriptor("BorderUnselectedButton16.png")); //$NON-NLS-1$
      put(SELECTED_BUTTON, createImageDescriptor("BorderSolarButton16.png")); //$NON-NLS-1$
      put(SURPLUS_BUTTON, createImageDescriptor("BorderBonusButton16.png")); //$NON-NLS-1$
    }

    private ImageDescriptor createImageDescriptor(String iconName) {
      URL resourceUrl = ResourceUtils.getResourceUrl(ITraitPluginConstants.PLUGIN_ID, "icons/" + iconName); //$NON-NLS-1$
      return ImageDescriptor.createFromURL(resourceUrl);
    }
  };
}