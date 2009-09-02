package net.sf.anathema.charms.character.editor.combo;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.charms.character.editor.ComboEditModel;
import net.sf.anathema.charms.character.editor.ComboEditorInput;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.lib.ui.ChangeableModelDisposable;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Text;

public class ComboTextControl {

  private static class UpdateWidgetsListener implements IChangeListener {
    private final ComboInputWidgets widgets;
    private final ComboEditModel editModel;

    private UpdateWidgetsListener(ComboInputWidgets widgets, ComboEditModel editModel) {
      this.widgets = widgets;
      this.editModel = editModel;
    }

    @Override
    public void stateChanged() {
      setText(widgets.name, editModel.getName());
      setText(widgets.description, editModel.getDescription());
    }

    private void setText(Text text, String value) {
      String displayText = getDisplayText(value);
      if (!text.getText().equals(displayText)) {
        text.setText(displayText);
      }
    }

    private String getDisplayText(String name) {
      return name == null ? "" : name;
    }
  }

  private static final class UpdateModelListener extends KeyAdapter {
    private final ComboInputWidgets widgets;
    private final ComboEditModel editModel;

    private UpdateModelListener(ComboInputWidgets widgets, ComboEditModel editModel) {
      this.widgets = widgets;
      this.editModel = editModel;
    }

    @Override
    public void keyReleased(KeyEvent e) {
      editModel.setName(widgets.name.getText());
      editModel.setDescription(widgets.description.getText());
    }
  }

  private final ComboEditModel editModel;
  private final AggregatedDisposable disposables;

  public ComboTextControl(ComboEditorInput editorInput, AggregatedDisposable disposables) {
    this.disposables = disposables;
    this.editModel = editorInput.getComboEditModel();
  }

  public void initControl(final ComboInputWidgets widgets) {
    initModelUpdate(widgets);
    initWidgetsUpdate(widgets);
  }

  private void initModelUpdate(final ComboInputWidgets widgets) {
    UpdateModelListener updateModelListener = new UpdateModelListener(widgets, editModel);
    widgets.description.addKeyListener(updateModelListener);
    widgets.name.addKeyListener(updateModelListener);
  }

  private void initWidgetsUpdate(final ComboInputWidgets widgets) {
    IChangeListener updateWidgetsListener = new UpdateWidgetsListener(widgets, editModel);
    editModel.addChangeListener(updateWidgetsListener);
    updateWidgetsListener.stateChanged();
    disposables.addDisposable(new ChangeableModelDisposable(editModel, updateWidgetsListener));
  }
}