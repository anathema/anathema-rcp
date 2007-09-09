package net.sf.anathema.character.attributes.model;

import org.eclipse.swt.graphics.Image;

import net.sf.anathema.character.attributes.AttributesPlugin;
import net.sf.anathema.character.trait.groupeditor.ISurplusIntViewImageProvider;

public class SurplusIntViewImageProvider implements ISurplusIntViewImageProvider {
  @Override
  public Image createPassiveImage() {
    return createImage(AttributesPlugin.UNSELECTED_BUTTON);
  }

  @Override
  public Image createActiveImage() {
    return createImage(AttributesPlugin.SELECTED_BUTTON);
  }

  @Override
  public Image createSurplusImage() {
    return createImage(AttributesPlugin.SURPLUS_BUTTON);
  }

  private Image createImage(String imageName) {
    return AttributesPlugin.getDefaultInstance().getImageRegistry().get(imageName);
  }
}