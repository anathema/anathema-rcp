package net.sf.anathema.character.trait.groupeditor;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.trait.display.ITraitStatusProvider;

import org.eclipse.swt.widgets.Button;

public final class FavorizationModelListener implements IChangeListener {
  private final Button favoredButton;
  private final IImageProvider buttonImageProvider;
  private final ITraitStatusProvider statusProvider;

  public FavorizationModelListener(
      Button favoredButton,
      ITraitStatusProvider statusProvider,
      IImageProvider buttonImageProvider) {
    this.favoredButton = favoredButton;
    this.statusProvider = statusProvider;
    this.buttonImageProvider = buttonImageProvider;
    stateChanged();
  }

  @Override
  public void stateChanged() {
    boolean favored = statusProvider.getStatus().isCheap();
    favoredButton.setSelection(favored);
    favoredButton.setImage(buttonImageProvider.getImage());
  }
}