package net.sf.anathema.notes;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.IItemEditorInput;
import net.sf.anathema.basics.item.data.IBasicItemData;
import net.sf.anathema.basics.item.data.IItemDescription;
import net.sf.anathema.basics.item.persistence.BasicDataItemPersister;
import net.sf.anathema.basics.jface.text.SimpleTextView;
import net.sf.anathema.basics.jface.text.StyledTextView;
import net.sf.anathema.editor.styledtext.IStyledTextEditor;
import net.sf.anathema.editor.styledtext.ITextModification;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.textualdescription.IStyledTextualDescription;
import net.sf.anathema.lib.textualdescription.ITextView;
import net.sf.anathema.lib.textualdescription.ITextualDescription;
import net.sf.anathema.lib.textualdescription.StyledTextPresenter;
import net.sf.anathema.lib.textualdescription.TextualPresenter;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class NotesEditor extends EditorPart implements IStyledTextEditor {

  private IItem<IBasicItemData> item;
  private StyledTextView contentView;
  private final BasicDataItemPersister persister = new BasicDataItemPersister();

  @Override
  public void doSave(IProgressMonitor monitor) {
    IItemEditorInput editorInput = getItemEditorInput();
    try {
      editorInput.save(persister);
    }
    catch (Exception e) {
      // TODO Fehlerhandling
      e.printStackTrace();
    }
    firePropertyChange(PROP_DIRTY);
  }

  private IItemEditorInput getItemEditorInput() {
    return (IItemEditorInput) getEditorInput();
  }

  @Override
  public void doSaveAs() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void init(IEditorSite site, IEditorInput input) throws PartInitException {
    try {
      IItemEditorInput itemInput = (IItemEditorInput) input;
      item = itemInput.loadItem(persister);
      item.addDirtyListener(new IChangeListener() {
        public void changeOccured() {
          firePropertyChange(PROP_DIRTY);
        }
      });
      setSite(site);
      setInput(input);
      getItemDescription().getName().addTextChangedListener(new IObjectValueChangedListener<String>() {
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

  private IItemDescription getItemDescription() {
    return item.getItemData().getDescription();
  }

  private void updatePartName() {
    String name = getItemDescription().getName().getText();
    if (StringUtilities.isNullOrEmpty(name)) {
      name = "Untitled Note";
    }
    setPartName(name);
  }

  @Override
  public boolean isDirty() {
    return item.isDirty();
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
    final ITextualDescription nameModel = getItemDescription().getName();
    new TextualPresenter(nameView, nameModel).initPresentation();
    Label contentLabel = new Label(parent, SWT.LEFT);
    contentLabel.setText("Content:"); //$NON-NLS-1$
    contentLabel.setLayoutData(createLabelData());
    final IStyledTextualDescription contentDescription = getItemDescription().getContent();
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
    IStyledTextualDescription styledText = getItemDescription().getContent();
    modification.perform(styledText, selectionRange.x, selectionRange.y);
  }

  @Override
  public boolean isActiveFor(ITextModification modification) {
    Point selectionRange = contentView.getSelectionRange();
    IStyledTextualDescription styledText = getItemDescription().getContent();
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