package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.character.trait.display.IDisplayTrait;

import org.eclipse.swt.graphics.Image;

public class FavorizationImageProvider implements IImageProvider {

  private final Image activeImage;
  private final Image passiveImage;
  private final IDisplayTrait trait;

  public FavorizationImageProvider(IDisplayTrait trait, Image passiveImage, Image activeImage) {
    this.trait = trait;
    this.passiveImage = passiveImage;
    this.activeImage = activeImage;
  }

  public Image getImage() {
    return trait.getFavorization().isFavored() ? activeImage : passiveImage;
  }
}