package net.sf.anathema.editor.styledtext.jface;

import java.util.Scanner;
import java.util.regex.MatchResult;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.basics.item.editor.AbstractPersistableItemEditorPart;
import net.sf.anathema.basics.item.editor.IEditorControl;
import net.sf.anathema.basics.item.editor.IPersistableItemEditor;
import net.sf.anathema.basics.item.text.ITitledText;
import net.sf.anathema.editor.styledtext.IStyledTextEditor;
import net.sf.anathema.editor.styledtext.ITextModification;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.projection.ProjectionDocument;
import org.eclipse.jface.text.projection.ProjectionDocumentManager;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
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

  private static final String CONTENT_TYPE_UNDERLINE = "UNDERLINE";
  private static final Token TOKEN = new Token(CONTENT_TYPE_UNDERLINE);

  private final class DemoViewerConfiguration extends SourceViewerConfiguration {
    @Override
    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
      // TODO Master-abhängiger Scanner
      RuleBasedPartitionScanner scanner = new RuleBasedPartitionScanner();

      scanner.setPredicateRules(new IPredicateRule[] { new MultiLineRule("<u>", "</u>", new Token(new TextAttribute(
          null,
          null,
          TextAttribute.UNDERLINE))) });
      DefaultDamagerRepairer repairer = new DefaultDamagerRepairer(scanner);
      PresentationReconciler reconciler = new PresentationReconciler();
      reconciler.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));
      reconciler.setDamager(repairer, IDocument.DEFAULT_CONTENT_TYPE);
      reconciler.setRepairer(repairer, IDocument.DEFAULT_CONTENT_TYPE);
      reconciler.setDamager(repairer, CONTENT_TYPE_UNDERLINE);
      reconciler.setRepairer(repairer, CONTENT_TYPE_UNDERLINE);
      return reconciler;
    }
  }

  @Override
  protected IEditorControl createItemEditorControl() {
    return new AbstractItemEditorControl(this) {

      @Override
      public void createPartControl(Composite parent) {
        final Document document = new Document();
        ProjectionDocumentManager manager = new ProjectionDocumentManager();
        final ProjectionDocument displayDocument = (ProjectionDocument) manager.createSlaveDocument(document);
        document.addDocumentListener(new IDocumentListener() {
          @Override
          public void documentAboutToBeChanged(DocumentEvent event) {
            // TODO Auto-generated method stub

          }

          @Override
          public void documentChanged(DocumentEvent event) {
            try {
              // TODO Über Event-Daten handlen, falls dort eine XML-Notation drinsteht
              Scanner scanner = new Scanner(document.get());
              String string;
              do {
                string = scanner.findInLine("(</?.?>)");
                if (string != null) {
                  MatchResult match = scanner.match();
                  displayDocument.removeMasterDocumentRange(match.start(), match.end() - match.start());
                }
              }
              while (string != null);
            }
            catch (BadLocationException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
          }
        });
        document.set("TEXT<u>line</u>ENDE");
        SourceViewerConfiguration configuration = new DemoViewerConfiguration();
        SourceViewer viewer = new SourceViewer(parent, null, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.BORDER);
        viewer.configure(configuration);
        viewer.setDocument(displayDocument);
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