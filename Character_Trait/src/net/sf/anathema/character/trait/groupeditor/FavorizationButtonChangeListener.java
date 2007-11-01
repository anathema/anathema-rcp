package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.character.trait.IInteractiveTrait;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public final class FavorizationButtonChangeListener implements Listener {
  private final IInteractiveTrait trait;
  private final Button button;

  public FavorizationButtonChangeListener(Button button, IInteractiveTrait trait) {
    this.button = button;
    this.trait = trait;
  }

  @Override
  public void handleEvent(Event event) {
    button.setSelection(!button.getSelection());
    trait.getFavorization().toggleFavored();
  }
}