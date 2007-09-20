package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.plugin.ICharacterCorePluginConstants;
import net.sf.anathema.character.trait.groupeditor.ISurplusIntViewImageProvider;
import net.sf.anathema.character.trait.resources.ITraitResources;

import org.eclipse.swt.graphics.Image;

public class SurplusIntViewImageProvider implements ISurplusIntViewImageProvider {
  
  private final String activeImageId;

  public SurplusIntViewImageProvider(String activeImageId) {
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

  @Override
  public Image createSurplusImage() {
    return createImage(ITraitResources.SURPLUS_BUTTON);
  }

  private Image createImage(String imageName) {
    return ICharacterCorePluginConstants.IMAGE_REGISTRY.get(imageName);
  }
}