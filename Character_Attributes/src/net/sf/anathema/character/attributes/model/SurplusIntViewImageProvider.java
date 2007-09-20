package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.trait.groupeditor.ISurplusIntViewImageProvider;
import net.sf.anathema.character.trait.resources.ITraitResources;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

public class SurplusIntViewImageProvider implements ISurplusIntViewImageProvider {
  
  private final ImageDescriptor activeImage;

  public SurplusIntViewImageProvider(ImageDescriptor activeImage) {
    // TODO Freigeben der Resource sinnvoll regeln
    this.activeImage = activeImage;
  }
  
  @Override
  public Image createPassiveImage() {
    return createImage(ITraitResources.UNSELECTED_BUTTON);
  }

  @Override
  public Image createActiveImage() {
    return activeImage.createImage(true);
  }

  @Override
  public Image createSurplusImage() {
    return createImage(ITraitResources.SURPLUS_BUTTON);
  }

  private Image createImage(String imageName) {
    return ITraitResources.IMAGE_REGISTRY.get(imageName);
  }
}