package net.sf.anathema.character.trait.status;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.trait.display.IDisplayTrait;

import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.swt.graphics.Image;

public interface ITraitStatusImageProvider extends IExecutableExtension {

  public Image getImage(IDisplayTrait trait, ICharacterId characterId);
}