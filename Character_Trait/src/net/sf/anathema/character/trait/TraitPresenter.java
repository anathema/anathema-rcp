package net.sf.anathema.character.trait;

import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.ui.IIntValueView;

public class TraitPresenter {

  public void initPresentation(final IDisplayTrait model, final IIntValueView view) {
    model.addValueChangeListener(new IChangeListener() {
      @Override
      public void changeOccured() {
        view.setValue(model.getValue());
      }
    });
    view.addIntValueChangedListener(new IIntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        model.setValue(newValue);
      }
    });
    view.setValue(model.getValue());
  }
}