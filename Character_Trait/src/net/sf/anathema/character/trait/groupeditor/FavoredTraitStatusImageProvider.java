package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.trait.display.IDisplayTrait;

import org.eclipse.swt.graphics.Image;

public class FavoredTraitStatusImageProvider extends AbstractExecutableExtension implements ITraitStatusImageProvider {

  private final Image favoredImage;

  public FavoredTraitStatusImageProvider(Image favoredImage) {
    this.favoredImage = favoredImage;
  }

  @Override
  public Image getImage(IDisplayTrait trait) {
    if (trait.getFavorization().isFavored()) {
      return favoredImage;
    }
    return null;
  }
}