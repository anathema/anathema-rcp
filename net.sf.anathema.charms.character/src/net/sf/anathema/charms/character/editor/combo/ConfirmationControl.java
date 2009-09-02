package net.sf.anathema.charms.character.editor.combo;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.charms.character.editor.ComboEditModel;
import net.sf.anathema.charms.character.editor.ComboEditorInput;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.lib.ui.ChangeableModelDisposable;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

public class ConfirmationControl {

  private static final class ActivateForValidModel implements IChangeListener {
    private final Button confirmButton;
    private final ComboEditModel model;

    public ActivateForValidModel(Button confirmButton, ComboEditModel model) {
      this.confirmButton = confirmButton;
      this.model = model;
      stateChanged();
    }

    @Override
    public void stateChanged() {
      confirmButton.setEnabled(model.isValid());
    }
  }

  private final ComboEditorInput editorInput;
  private final AggregatedDisposable disposables;

  public ConfirmationControl(ComboEditorInput editorInput, AggregatedDisposable disposables) {
    this.editorInput = editorInput;
    this.disposables = disposables;
  }

  public void initControl(final ComboInputWidgets widgets) {
    initConfirmationListening(widgets.confirmButton);
    initConfirmationActivation(widgets.confirmButton);
  }

  private void initConfirmationActivation(Button confirmButton) {
    ComboEditModel model = editorInput.getComboEditModel();
    ActivateForValidModel listener = new ActivateForValidModel(confirmButton, model);
    model.addChangeListener(listener);
    disposables.addDisposable(new ChangeableModelDisposable(model, listener));
  }

  private void initConfirmationListening(Button confirmButton) {
    confirmButton.addSelectionListener(new SelectionListener() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        widgetDefaultSelected(e);
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
        editorInput.learnCombo();
      }
    });
  }
}