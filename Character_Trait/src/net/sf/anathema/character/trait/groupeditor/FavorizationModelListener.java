package net.sf.anathema.character.trait.groupeditor;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.trait.status.ITraitStatusModel;

import org.eclipse.swt.widgets.Button;

public final class FavorizationModelListener implements IChangeListener {
  private final Button favoredButton;
  private final IImageProvider buttonImageProvider;
  private final ITraitStatusModel statusModel;

  public FavorizationModelListener(
      Button favoredButton,
      ITraitStatusModel statusModel,
      IImageProvider buttonImageProvider) {
    this.favoredButton = favoredButton;
    this.statusModel = statusModel;
    this.buttonImageProvider = buttonImageProvider;
    stateChanged();
  }

  @Override
  public void stateChanged() {
    boolean favored = statusModel.getStatus().isCheap();
    favoredButton.setSelection(favored);
    favoredButton.setImage(buttonImageProvider.getImage());
  }
}