package net.sf.anathema.character.core.traitview;

import org.eclipse.swt.graphics.Image;

public interface IIntValuePaintContext {

  public void drawImage(int index, Image image);

  public int getValue();
}