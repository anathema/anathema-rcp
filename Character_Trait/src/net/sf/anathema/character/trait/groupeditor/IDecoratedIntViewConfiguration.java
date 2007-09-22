package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.lib.util.IIdentificate;

import org.eclipse.swt.graphics.Image;

public interface IDecoratedIntViewConfiguration {

  public Image createPassiveImage();

  public Image createActiveImage();

  public Image createSurplusImage();
  
  public int getPointsCoveredByCredit(IIdentificate traitType);
}