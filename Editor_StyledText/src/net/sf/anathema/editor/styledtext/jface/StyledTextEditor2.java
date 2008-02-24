package net.sf.anathema.editor.styledtext.jface;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.basics.item.editor.AbstractPersistableItemEditorPart;
import net.sf.anathema.basics.item.editor.IEditorControl;
import net.sf.anathema.basics.item.editor.IPersistableItemEditor;
import net.sf.anathema.basics.item.text.ITitledText;
import net.sf.anathema.editor.styledtext.IStyledTextEditor;
import net.sf.anathema.editor.styledtext.ITextModification;

import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.formatter.ContentFormatter;
import org.eclipse.jface.text.formatter.IContentFormatter;
import org.eclipse.jface.text.formatter.IFormattingStrategy;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class StyledTextEditor2 extends AbstractPersistableItemEditorPart<ITitledText> implements
    IStyledTextEditor,
    IPersistableItemEditor {

  private final class DemoContentFormatter extends ContentFormatter {
    @Override
    public IFormattingStrategy getFormattingStrategy(String contentType) {
      return super.getFormattingStrategy(IDocument.DEFAULT_CONTENT_TYPE);
    }
  }

  private final class DemoFormattingStrategy implements IFormattingStrategy {
    @Override
    public String format(String content, boolean isLineStart, String indentation, int[] positions) {
      return content.replaceAll("!", "");
    }

    @Override
    public void formatterStarts(String initialIndentation) {
      // TODO Auto-generated method stub
    }

    @Override
    public void formatterStops() {
      // TODO Auto-generated method stub
    }
  }

  @Override
  protected IEditorControl createItemEditorControl() {
    return new AbstractItemEditorControl(this) {

      @Override
      public void createPartControl(Composite parent) {
        final ContentFormatter formatter = new DemoContentFormatter();
        formatter.setFormattingStrategy(new DemoFormattingStrategy(), IDocument.DEFAULT_CONTENT_TYPE);
        SourceViewer viewer = new SourceViewer(parent, null, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.BORDER);
        Document document = new Document();
        document.set("!!TEXT!!");
        SourceViewerConfiguration configuration = new SourceViewerConfiguration() {
          @Override
          public IContentFormatter getContentFormatter(ISourceViewer sourceViewer) {
            return formatter;
          }
        };
        viewer.configure(configuration);
        viewer.setDocument(document);
        viewer.doOperation(ISourceViewer.FORMAT);
      }

      @Override
      public void setFocus() {
        // TODO Auto-generated method stub

      }
    };
  }

  @Override
  public void addCaretChangeListener(IChangeListener changeListener) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean isActiveFor(ITextModification modification) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isSelectionEmpty() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void modifySelection(ITextModification modification) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeCaretChangeListener(IChangeListener caretChangeListener) {
    // TODO Auto-generated method stub

  }

}