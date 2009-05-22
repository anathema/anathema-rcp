package net.sf.anathema.character.experience.points;

import static java.text.MessageFormat.*;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

public final class EditEntryDoubleClickListener implements IDoubleClickListener {
  private final ExperiencePointsEditorInput editorInput;
  private final String title = "Edit Experience Entry";
  private final String message = "Type the revised entry. Deleting all text will delete the entry.";

  public EditEntryDoubleClickListener(ExperiencePointsEditorInput editorInput) {
    this.editorInput = editorInput;
  }

  @Override
  public void doubleClick(DoubleClickEvent event) {
    ExperienceEntry experienceEntry = getEditEntry(event);
    String initialValue = format("{0} {1}", experienceEntry.points, experienceEntry.comment); //$NON-NLS-1$
    String updatedValue = updateEntry(initialValue, event.getViewer());
    editorInput.update(experienceEntry, updatedValue);
  }

  private String updateEntry(String initialValue, Viewer viewer) {
    Shell shell = viewer.getControl().getShell();
    InputDialog dialog = new InputDialog(shell, title, message, initialValue, null);
    boolean isConfirmed = dialog.open() == Window.OK;
    return isConfirmed ? dialog.getValue() : initialValue;
  }

  private ExperienceEntry getEditEntry(DoubleClickEvent event) {
    StructuredSelection selection = (StructuredSelection) event.getSelection();
    return (ExperienceEntry) selection.getFirstElement();
  }
}