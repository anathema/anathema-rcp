package net.sf.anathema.character.trait.display;

import net.sf.anathema.character.core.plugin.ICharacterCorePluginConstants;
import net.sf.anathema.character.trait.groupeditor.dynamic.IIntViewImageProvider;
import net.sf.anathema.character.trait.resources.ITraitResources;

import org.eclipse.swt.graphics.Image;

public class IntViewImageProvider implements IIntViewImageProvider {
  
  private final String activeImageId;

  public IntViewImageProvider(String activeImageId) {
    this.activeImageId = activeImageId;
  }
  
  @Override
  public Image createPassiveImage() {
    return createImage(ITraitResources.UNSELECTED_BUTTON);
  }

  @Override
  public Image createActiveImage() {
    return createImage(activeImageId);
  }

  private Image createImage(String imageName) {
    return ICharacterCorePluginConstants.IMAGE_REGISTRY.get(imageName);
  }
}