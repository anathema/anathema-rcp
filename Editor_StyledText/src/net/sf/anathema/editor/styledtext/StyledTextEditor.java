package net.sf.anathema.editor.styledtext;

import net.sf.anathema.basics.item.IPersistableEditorInput;
import net.sf.anathema.basics.item.data.ITitledText;
import net.sf.anathema.basics.jface.text.SimpleTextView;
import net.sf.anathema.basics.jface.text.StyledTextView;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.textualdescription.IStyledTextualDescription;
import net.sf.anathema.lib.textualdescription.ITextView;
import net.sf.anathema.lib.textualdescription.ITextualDescription;
import net.sf.anathema.lib.textualdescription.StyledTextPresenter;
import net.sf.anathema.lib.textualdescription.TextualPresenter;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class StyledTextEditor extends EditorPart implements IStyledTextEditor {

  private final class SaveEditorJob extends Job {
    private final Display display;

    /** Constructor has to be called from display thread to get the correct Display for execution. */
    private SaveEditorJob(String name) {
      super(name);
      this.display = Display.getCurrent();
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
      monitor.beginTask(Messages.StyledTextEditor_SaveJobTask, IProgressMonitor.UNKNOWN);
      IPersistableEditorInput<ITitledText> editorInput = getItemEditorInput();
      try {
        editorInput.save(monitor);
        display.asyncExec(new FireDirtyRunnable());
        return Status.OK_STATUS;
      }
      catch (Exception e) {
        String message = Messages.StyledTextEditor_SaveErrorMessage;
        return StyledTextPlugin.createErrorStatus(NLS.bind(message, editorInput.getName()), e);
      }
      finally {
        monitor.done();
      }
    }
  }

  private final class FireDirtyRunnable implements Runnable {
    @Override
    public void run() {
      firePropertyChange(PROP_DIRTY);
    }
  }

  private ITitledText itemData;
  private StyledTextView contentView;

  @Override
  public void doSave(IProgressMonitor monitor) {
    String message = Messages.StyledTextEditor_SaveJobTitle;
    Job saveJob = new SaveEditorJob(NLS.bind(message, getEditorInput().getName()));
    saveJob.setRule(ResourcesPlugin.getWorkspace().getRoot());
    saveJob.schedule();
  }

  @SuppressWarnings("unchecked")
  private IPersistableEditorInput<ITitledText> getItemEditorInput() {
    return (IPersistableEditorInput<ITitledText>) getEditorInput();
  }

  @Override
  public void doSaveAs() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void init(IEditorSite site, IEditorInput input) throws PartInitException {
    try {
      setInput(input);
      IPersistableEditorInput<ITitledText> itemInput = getItemEditorInput();
      itemData = itemInput.loadItem();
      itemData.addDirtyListener(new IChangeListener() {
        public void changeOccured() {
          firePropertyChange(PROP_DIRTY);
        }
      });
      setSite(site);
      setTitleImage(itemInput.getImageDescriptor().createImage());
      itemData.getName().addTextChangedListener(new IObjectValueChangedListener<String>() {
        @Override
        public void valueChanged(String newValue) {
          updatePartName();
        }
      });
      updatePartName();
    }
    catch (Exception e) {
      throw new PartInitException("Error initializing styled text editor.", e); //$NON-NLS-1$
    }
  }

  private void updatePartName() {
    setPartName(getItemEditorInput().getName());
  }

  @Override
  public boolean isDirty() {
    return itemData.isDirty();
  }

  @Override
  public boolean isSaveAsAllowed() {
    return false;
  }

  @Override
  public void createPartControl(Composite parent) {
    parent.setLayout(new GridLayout(2, false));
    Label nameLabel = new Label(parent, SWT.LEFT);
    nameLabel.setText("Name:"); //$NON-NLS-1$
    nameLabel.setLayoutData(createLabelData());
    final ITextView nameView = new SimpleTextView(parent);
    final ITextualDescription nameModel = itemData.getName();
    new TextualPresenter(nameView, nameModel).initPresentation();
    Label contentLabel = new Label(parent, SWT.LEFT);
    contentLabel.setText("Content:"); //$NON-NLS-1$
    contentLabel.setLayoutData(createLabelData());
    final IStyledTextualDescription contentDescription = itemData.getContent();
    contentView = new StyledTextView(parent);
    new StyledTextPresenter(contentView, contentDescription).initPresentation();
    getSite().setSelectionProvider(contentView.createSelectionProvider());
  }

  private GridData createLabelData() {
    return new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false);
  }

  @Override
  public void setFocus() {
    contentView.setFocus();
  }

  @Override
  public void modifySelection(ITextModification modification) {
    Point selectionRange = contentView.getSelectionRange();
    IStyledTextualDescription styledText = itemData.getContent();
    modification.perform(styledText, selectionRange.x, selectionRange.y);
  }

  @Override
  public boolean isActiveFor(ITextModification modification) {
    Point selectionRange = contentView.getSelectionRange();
    IStyledTextualDescription styledText = itemData.getContent();
    return modification.isActive(styledText, selectionRange.x, selectionRange.y);
  }

  @Override
  public boolean isSelectionEmpty() {
    Point selectionRange = contentView.getSelectionRange();
    return selectionRange.y == 0;
  }

  @Override
  public void addCaretChangeListener(IChangeListener changeListener) {
    contentView.addCursorPositionChangedListener(changeListener);
  }

  @Override
  public void removeCaretChangeListener(IChangeListener changeListener) {
    contentView.removeCursorPositionChangedListener(changeListener);
  }
}