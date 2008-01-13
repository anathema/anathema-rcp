package net.sf.anathema.character.trait.groupeditor;

import java.util.List;

import net.sf.anathema.character.trait.display.IDisplayTrait;

import org.eclipse.swt.graphics.Image;

public class FavorizationImageProvider implements IImageProvider {

  private final Image passiveImage;
  private final IDisplayTrait trait;
  private final Iterable<ITraitStatusImageProvider>  imageProviders;

  public FavorizationImageProvider(
      IDisplayTrait trait,
      Image passiveImage,
      Iterable<ITraitStatusImageProvider> imageProviders) {
    this.trait = trait;
    this.passiveImage = passiveImage;
    this.imageProviders = imageProviders;
  }

  public Image getImage() {
    for (ITraitStatusImageProvider statusImageProvder : imageProviders) {
      Image statusImage = statusImageProvder.getImage(trait);
      if (statusImage != null) {
        return statusImage;
      }
    }
    return passiveImage;
  }
}