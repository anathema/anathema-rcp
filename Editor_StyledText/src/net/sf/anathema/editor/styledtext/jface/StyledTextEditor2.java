package net.sf.anathema.editor.styledtext.jface;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.basics.item.editor.AbstractPersistableItemEditorPart;
import net.sf.anathema.basics.item.editor.IEditorControl;
import net.sf.anathema.basics.item.editor.UpdatePartNameListener;
import net.sf.anathema.basics.item.text.ITitledText;
import net.sf.anathema.basics.swt.event.KeyReleasedChangeAdapter;
import net.sf.anathema.basics.swt.event.MouseUpChangeAdapter;
import net.sf.anathema.editor.styledtext.IStyledTextEditor;
import net.sf.anathema.editor.styledtext.ITextModification;
import net.sf.anathema.editor.styledtext.TextChangeListenerDisposable;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.textualdescription.IStyledTextualDescription;
import net.sf.anathema.lib.textualdescription.IStyledTextualDescription2;
import net.sf.anathema.lib.textualdescription.ITextPart;
import net.sf.anathema.lib.textualdescription.ITextualDescription;

import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;

public class StyledTextEditor2 extends AbstractPersistableItemEditorPart<ITitledText> implements
    IStyledTextEditor {

  private SourceViewer viewer;
  private Document document;
  private STE2Partitioner partitioner;
  private final ChangeControl changeControl = new ChangeControl();

  private ITitledText getItem() {
    return getPersistableEditorInput().getItem();
  }

  @Override
  protected IEditorControl createItemEditorControl() {
    return new AbstractItemEditorControl(this) {

      @Override
      public void init(IEditorSite editorSite, IEditorInput input) {
        super.init(editorSite, input);
        final IObjectValueChangedListener<String> listener = new UpdatePartNameListener(StyledTextEditor2.this);
        final ITextualDescription name = getItem().getName();
        addDisposable(new TextChangeListenerDisposable(name, listener));
        name.addTextChangedListener(listener);
      }

      @Override
      public void createPartControl(Composite parent) {
        document = new Document();
        partitioner = new STE2Partitioner();
        document.setDocumentPartitioner(partitioner);
        partitioner.connect(document);
        viewer = new SourceViewer(parent, null, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.BORDER);
        StyledText textWidget = viewer.getTextWidget();
        textWidget.addKeyListener(new KeyReleasedChangeAdapter(changeControl));
        textWidget.addMouseListener(new MouseUpChangeAdapter(changeControl));
        getSite().setSelectionProvider(viewer.getSelectionProvider());
        document.set(createDocumentText());
        document.addDocumentListener(new IDocumentListener() {
          @Override
          public void documentAboutToBeChanged(DocumentEvent event) {
            // nothing to do
          }

          @Override
          public void documentChanged(DocumentEvent event) {
            // TODO Change Length of Styled TextPart
            getItem().getContent().replaceText(event.getOffset(), event.getLength(), event.getText());
          }
        });
        partitioner.setPartitioning((IStyledTextualDescription2) getItem().getContent());
        viewer.configure(new STE2ViewerConfiguration(partitioner));
        viewer.setDocument(document);
      }

      private String createDocumentText() {
        StringBuilder builder = new StringBuilder();
        for (ITextPart part : getItem().getContent().getTextParts()) {
          builder.append(part.getText());
        }
        return builder.toString();
      }

      @Override
      public void setFocus() {
        viewer.getTextWidget().setFocus();
      }
    };
  }

  @Override
  public boolean isActiveFor(ITextModification modification) {
    Point selectedRange = viewer.getSelectedRange();
    IStyledTextualDescription styledText = getItem().getContent();
    return modification.isActive(styledText, selectedRange.x, selectedRange.y);
  }

  @Override
  public boolean isSelectionEmpty() {
    return viewer.getSelection().isEmpty();
  }

  @Override
  public void modifySelection(ITextModification modification) {
    Point selectedRange = viewer.getSelectedRange();
    modification.perform(partitioner, selectedRange.x, viewer.getSelectedRange().y);
  }

  @Override
  public void addCaretChangeListener(IChangeListener changeListener) {
    changeControl.addChangeListener(changeListener);
  }

  @Override
  public void removeCaretChangeListener(IChangeListener changeListener) {
    changeControl.removeChangeListener(changeListener);
  }
}