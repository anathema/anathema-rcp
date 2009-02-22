package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.status.ITraitStatusImageProvider;

import org.eclipse.swt.graphics.Image;

public class StaticTraitStatusImageProvider extends UnconfiguredExecutableExtension implements ITraitStatusImageProvider {

  private final Image image;

  public StaticTraitStatusImageProvider(Image image) {
    this.image = image;
  }

  @Override
  public Image getImage(IDisplayTrait trait, ICharacterId characterId) {
    return image;
  }

  @Override
  public boolean hasImage(IDisplayTrait trait, ICharacterId characterId) {
    return true;
  }
}