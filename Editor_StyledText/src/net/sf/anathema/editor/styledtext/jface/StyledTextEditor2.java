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
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.formatter.ContentFormatter;
import org.eclipse.jface.text.formatter.IFormattingStrategy;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class StyledTextEditor2 extends AbstractPersistableItemEditorPart<ITitledText> implements
    IStyledTextEditor,
    IPersistableItemEditor {

  private static final String CONTENT_TYPE_UNDERLINE = "BOLD";
  private static final Token TOKEN = new Token(CONTENT_TYPE_UNDERLINE);

  private final class DemoContentFormatter extends ContentFormatter {
    @Override
    public IFormattingStrategy getFormattingStrategy(String contentType) {
      return super.getFormattingStrategy(IDocument.DEFAULT_CONTENT_TYPE);
    }
  }

  private final class DemoFormattingStrategy implements IFormattingStrategy {
    @Override
    public String format(String content, boolean isLineStart, String indentation, int[] positions) {
      return content.replaceAll("<.?b>", "");
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
        Document document = createDocument();
        FastPartitioner partitioner = createPartitioner();
        partitioner.connect(document);
        document.setDocumentPartitioner(partitioner);
        final ContentFormatter formatter = new DemoContentFormatter();
        formatter.setFormattingStrategy(new DemoFormattingStrategy(), IDocument.DEFAULT_CONTENT_TYPE);
        SourceViewerConfiguration configuration = new SourceViewerConfiguration() {
          // @Override
          // public IContentFormatter getContentFormatter(ISourceViewer sourceViewer) {
          // return formatter;
          // }

          @Override
          public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
            RuleBasedPartitionScanner scanner = new RuleBasedPartitionScanner();

            scanner.setPredicateRules(new IPredicateRule[] { new MultiLineRule("<u>", "</u>", new Token(
                new TextAttribute(null, null, TextAttribute.UNDERLINE))) });
            DefaultDamagerRepairer repairer = new DefaultDamagerRepairer(scanner);
            PresentationReconciler reconciler = new PresentationReconciler();
            reconciler.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));
            reconciler.setDamager(repairer, IDocument.DEFAULT_CONTENT_TYPE);
            reconciler.setRepairer(repairer, IDocument.DEFAULT_CONTENT_TYPE);
            reconciler.setDamager(repairer, CONTENT_TYPE_UNDERLINE);
            reconciler.setRepairer(repairer, CONTENT_TYPE_UNDERLINE);
            return reconciler;
          }
        };
        SourceViewer viewer = new SourceViewer(parent, null, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.BORDER);
        viewer.configure(configuration);
        viewer.setDocument(document);
        // viewer.doOperation(ISourceViewer.FORMAT);
      }

      private Document createDocument() {
        Document document = new Document();
        document.set("TEXT<u>line</u>ENDE");
        return document;
      }

      private FastPartitioner createPartitioner() {
        RuleBasedPartitionScanner scanner = new RuleBasedPartitionScanner();
        scanner.setPredicateRules(new IPredicateRule[] { new MultiLineRule("<u>", "</u>", TOKEN) });
        return new FastPartitioner(scanner, new String[] { IDocument.DEFAULT_CONTENT_TYPE, CONTENT_TYPE_UNDERLINE }) {
          @Override
          public ITypedRegion[] computePartitioning(int offset, int length, boolean includeZeroLengthPartitions) {
            ITypedRegion[] regions = super.computePartitioning(offset, length, includeZeroLengthPartitions);
            StringBuilder builder = new StringBuilder();
            for (ITypedRegion region : regions) {
              builder.append(region.getType()
                  + " from "
                  + region.getOffset()
                  + " to "
                  + (region.getOffset() + region.getLength()));
              builder.append("\n");
            }
            System.out.print(builder);
            return regions;
          }
        };
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