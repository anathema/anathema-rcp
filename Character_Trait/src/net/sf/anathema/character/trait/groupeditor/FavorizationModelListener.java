package net.sf.anathema.character.trait.groupeditor;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.trait.display.IDisplayTrait;

import org.eclipse.swt.widgets.Button;

public final class FavorizationModelListener implements IChangeListener {
  private final Button favoredButton;
  private final IDisplayTrait trait;
  private final IImageProvider buttonImageProvider;

  public FavorizationModelListener(
      Button favoredButton,
      IDisplayTrait trait,
      IImageProvider buttonImageProvider) {
    this.favoredButton = favoredButton;
    this.trait = trait;
    this.buttonImageProvider = buttonImageProvider;
    stateChanged();
  }

  @Override
  public void stateChanged() {
    boolean favored = trait.getFavorization().isFavored();
    favoredButton.setSelection(favored);
    favoredButton.setImage(buttonImageProvider.getImage());
  }
}