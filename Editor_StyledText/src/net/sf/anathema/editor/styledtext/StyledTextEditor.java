package net.sf.anathema.editor.styledtext;

import net.sf.anathema.basics.item.IPersistableEditorInput;
import net.sf.anathema.basics.item.editor.AbstractPersistableItemEditorPart;
import net.sf.anathema.basics.item.editor.IPersistableItemEditor;
import net.sf.anathema.basics.item.editor.UpdatePartNameListener;
import net.sf.anathema.basics.item.text.ITitledText;
import net.sf.anathema.basics.jface.text.SimpleTextView;
import net.sf.anathema.basics.jface.text.StyledTextView;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.textualdescription.IStyledTextualDescription;
import net.sf.anathema.lib.textualdescription.ITextView;
import net.sf.anathema.lib.textualdescription.ITextualDescription;
import net.sf.anathema.lib.textualdescription.StyledTextPresenter;
import net.sf.anathema.lib.textualdescription.TextualPresenter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

public class StyledTextEditor extends AbstractPersistableItemEditorPart implements
    IStyledTextEditor,
    IPersistableItemEditor {

  private ITitledText titledText;
  private StyledTextView contentView;

  @Override
  @SuppressWarnings("unchecked")
  public IPersistableEditorInput<ITitledText> getEditorInput() {
    return (IPersistableEditorInput<ITitledText>) super.getEditorInput();
  }

  @Override
  public void init(IEditorSite site, IEditorInput input) throws PartInitException {
    try {
      setInput(input);
      IPersistableEditorInput<ITitledText> itemInput = getEditorInput();
      titledText = itemInput.getItem();
      getItem().addDirtyListener(new IChangeListener() {
        public void changeOccured() {
          firePropertyChange(PROP_DIRTY);
        }
      });
      setSite(site);
      setTitleImage(itemInput.getImageDescriptor().createImage());
      getItem().getName().addTextChangedListener(new UpdatePartNameListener(this));
      setPartName(getEditorInput().getName());
    }
    catch (Exception e) {
      throw new PartInitException("Error initializing styled text editor.", e); //$NON-NLS-1$
    }
  }

  @Override
  protected ITitledText getItem() {
    return titledText;
  }

  @Override
  public void createPartControl(Composite parent) {
    parent.setLayout(new GridLayout(2, false));
    Label nameLabel = new Label(parent, SWT.LEFT);
    nameLabel.setText(getColonLabel(Messages.StyledTextEditor_Name));
    nameLabel.setLayoutData(createLabelData());
    final ITextView nameView = SimpleTextView.createSingleLineView(parent);
    final ITextualDescription nameModel = getItem().getName();
    new TextualPresenter(nameView, nameModel).initPresentation();
    Label contentLabel = new Label(parent, SWT.LEFT);
    contentLabel.setText(getColonLabel(Messages.StyledTextEditor_Content));
    contentLabel.setLayoutData(createLabelData());
    final IStyledTextualDescription contentDescription = getItem().getContent();
    contentView = new StyledTextView(parent);
    new StyledTextPresenter(contentView, contentDescription).initPresentation();
    getSite().setSelectionProvider(contentView.createSelectionProvider());
  }

  private String getColonLabel(String string) {
    return string + ':';
  }

  protected GridData createLabelData() {
    return new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false);
  }

  @Override
  public void setFocus() {
    contentView.setFocus();
  }

  @Override
  public void modifySelection(ITextModification modification) {
    Point selectionRange = contentView.getSelectionRange();
    IStyledTextualDescription styledText = getItem().getContent();
    modification.perform(styledText, selectionRange.x, selectionRange.y);
  }

  @Override
  public boolean isActiveFor(ITextModification modification) {
    Point selectionRange = contentView.getSelectionRange();
    IStyledTextualDescription styledText = getItem().getContent();
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