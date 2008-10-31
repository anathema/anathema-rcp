package net.sf.anathema.character.trait.groupeditor;

import org.eclipse.swt.graphics.Image;

public interface IIntViewImageProvider {

  public Image createPassiveImage();

  public Image createActiveImage();
}