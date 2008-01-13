package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.character.trait.display.IDisplayTrait;

import org.eclipse.swt.graphics.Image;

public interface ITraitStatusImageProvider {

  public Image getImage(IDisplayTrait trait);
}