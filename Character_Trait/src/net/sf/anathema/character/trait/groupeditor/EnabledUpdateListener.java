package net.sf.anathema.character.trait.groupeditor;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.trait.IInteractiveTrait;

import org.eclipse.swt.widgets.Button;

public final class EnabledUpdateListener implements IChangeListener {
  private final Button favoredButton;
  private final IInteractiveTrait trait;

  public EnabledUpdateListener(Button favoredButton, IInteractiveTrait trait) {
    this.favoredButton = favoredButton;
    this.trait = trait;
    stateChanged();
  }

  @Override
  public void stateChanged() {
    favoredButton.setEnabled(trait.getFavorization().isFavorable());
  }
}