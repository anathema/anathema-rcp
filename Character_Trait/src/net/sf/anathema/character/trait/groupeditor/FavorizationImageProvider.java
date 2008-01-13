package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.status.ITraitStatusImageProvider;

import org.eclipse.swt.graphics.Image;

public class FavorizationImageProvider implements IImageProvider {

  private final Image passiveImage;
  private final IDisplayTrait trait;
  private final Iterable<ITraitStatusImageProvider> imageProviders;
  private ICharacterId characterId;

  public FavorizationImageProvider(
      IDisplayTrait trait,
      Image passiveImage,
      Iterable<ITraitStatusImageProvider> imageProviders,
      ICharacterId characterId) {
    this.characterId = characterId;
    this.trait = trait;
    this.passiveImage = passiveImage;
    this.imageProviders = imageProviders;
  }

  public Image getImage() {
    for (ITraitStatusImageProvider statusImageProvider : imageProviders) {
      Image statusImage = statusImageProvider.getImage(trait, characterId);
      if (statusImage != null) {
        return statusImage;
      }
    }
    return passiveImage;
  }
}