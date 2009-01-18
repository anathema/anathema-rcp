package net.sf.anathema.character.trait.groupeditor;

import java.util.List;

import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.CollectionUtilities;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.status.ITraitStatusImageProvider;

import org.eclipse.swt.graphics.Image;

public class FavorizationImageProvider implements IImageProvider {

  private static final class WithImageForTrait implements IPredicate<ITraitStatusImageProvider> {
    private final IDisplayTrait trait;
    private final ICharacterId characterId;

    public WithImageForTrait(IDisplayTrait trait, ICharacterId characterId) {
      this.trait = trait;
      this.characterId = characterId;
    }

    @Override
    public boolean evaluate(ITraitStatusImageProvider candidate) {
      return candidate.hasImage(trait, characterId);
    }
  }

  private final IDisplayTrait trait;
  private final Iterable<ITraitStatusImageProvider> imageProviders;
  private final ICharacterId characterId;
  private final WithImageForTrait withImageForTrait;

  public FavorizationImageProvider(
      IDisplayTrait trait,
      Image passiveImage,
      List<ITraitStatusImageProvider> imageProviders,
      ICharacterId characterId) {
    this.characterId = characterId;
    this.trait = trait;
    imageProviders.add(new StaticTraitStatusImageProvider(passiveImage));
    this.imageProviders = imageProviders;
    this.withImageForTrait = new WithImageForTrait(trait, characterId);
  }

  public Image getImage() {
    ITraitStatusImageProvider imageProvider = CollectionUtilities.getFirst(imageProviders, withImageForTrait);
    return imageProvider.getImage(trait, characterId);
  }
}