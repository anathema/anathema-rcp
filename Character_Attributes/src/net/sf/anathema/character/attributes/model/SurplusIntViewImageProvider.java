package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.trait.groupeditor.ISurplusIntViewImageProvider;
import net.sf.anathema.character.trait.resources.ITraitResources;

import org.eclipse.swt.graphics.Image;

public class SurplusIntViewImageProvider implements ISurplusIntViewImageProvider {
  @Override
  public Image createPassiveImage() {
    return createImage(ITraitResources.UNSELECTED_BUTTON);
  }

  @Override
  public Image createActiveImage() {
    return createImage(ITraitResources.SELECTED_BUTTON);
  }

  @Override
  public Image createSurplusImage() {
    return createImage(ITraitResources.SURPLUS_BUTTON);
  }

  private Image createImage(String imageName) {
    return ITraitResources.IMAGE_REGISTRY.get(imageName);
  }
}