package net.sf.anathema.editor.styledtext;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.basics.item.editor.AbstractPersistableItemEditorPart;
import net.sf.anathema.basics.item.editor.IEditorControl;
import net.sf.anathema.basics.item.editor.UpdatePartNameListener;
import net.sf.anathema.basics.item.text.ITitledText;
import net.sf.anathema.basics.jface.text.SimpleTextView;
import net.sf.anathema.basics.jface.text.StyledTextView;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
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
import org.eclipse.ui.forms.widgets.FormToolkit;

public class StyledTextEditor extends AbstractPersistableItemEditorPart<ITitledText> implements IStyledTextEditor {

  private StyledTextView contentView;

  private ITitledText getItem() {
    return getPersistableEditorInput().getItem();
  }

  @Override
  protected IEditorControl createItemEditorControl() {
    return new AbstractItemEditorControl(this) {

      @Override
      public void setFocus() {
        contentView.setFocus();
      }

      @Override
      public void init(IEditorSite editorSite, IEditorInput input) {
        super.init(editorSite, input);
        final IObjectValueChangedListener<String> listener = new UpdatePartNameListener(StyledTextEditor.this);
        final ITextualDescription name = getItem().getName();
        addDisposable(new TextChangeListenerDisposable(name, listener));
        name.addTextChangedListener(listener);
      }

      @Override
      protected void createPartControl(FormToolkit toolkit, Composite body) {
        body.setLayout(new GridLayout(2, false));
        Label nameLabel = toolkit.createLabel(body, getColonLabel(Messages.StyledTextEditor_Name), SWT.LEFT);
        nameLabel.setLayoutData(createLabelData());
        final ITextView nameView = SimpleTextView.createSingleLineView(body, toolkit);
        final ITextualDescription nameModel = getItem().getName();
        addDisposable(new TextualPresenter(nameView, nameModel)).initPresentation();
        Label contentLabel = toolkit.createLabel(body, getColonLabel(Messages.StyledTextEditor_Content), SWT.LEFT);
        contentLabel.setLayoutData(createLabelData());
        final IStyledTextualDescription contentDescription = getItem().getContent();
        contentView = new StyledTextView(body);
        new StyledTextPresenter(contentView, contentDescription).initPresentation();
        getSite().setSelectionProvider(contentView.createSelectionProvider());
      }

      private String getColonLabel(String string) {
        return string + ':';
      }

      protected GridData createLabelData() {
        return new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false);
      }
    };
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