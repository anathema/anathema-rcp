package net.sf.anathema.character.trait.groupeditor;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.trait.IDisplayTrait;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;

public final class FavorizationModelListener implements IChangeListener {
  private final Button favoredButton;
  private final IDisplayTrait trait;
  private final Image passiveImage;
  private final Image activeImage;

  public FavorizationModelListener(Button favoredButton, IDisplayTrait trait, Image passiveImage, Image activeImage) {
    this.favoredButton = favoredButton;
    this.trait = trait;
    this.passiveImage = passiveImage;
    this.activeImage = activeImage;
    stateChanged();
  }

  @Override
  public void stateChanged() {
    boolean favored = trait.getFavorization().isFavored();
    favoredButton.setSelection(favored);
    favoredButton.setImage(favored ? activeImage : passiveImage);
  }
}