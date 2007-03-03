package editor.styledtext.editors;

import java.io.ByteArrayInputStream;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.basics.jface.IFileEditorInput;
import net.sf.anathema.basics.jface.IStorageEditorInput;
import net.sf.anathema.basics.jface.selection.StyledTextSelectionProvider;
import net.sf.anathema.framework.item.data.BasicItemData;
import net.sf.anathema.framework.item.data.BasicsPersister;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.textualdescription.IStyledTextChangeListener;
import net.sf.anathema.lib.textualdescription.IStyledTextualDescription;
import net.sf.anathema.lib.textualdescription.ITextFormat;
import net.sf.anathema.lib.textualdescription.ITextPart;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ExtendedModifyEvent;
import org.eclipse.swt.custom.ExtendedModifyListener;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class StyledTextEditor extends EditorPart implements IStyledTextEditor {

  private BasicItemData itemData;
  private StyledText contentComposite;

  @Override
  public void doSave(IProgressMonitor monitor) {
    IFileEditorInput editorInput = (IFileEditorInput) getEditorInput();
    BasicsPersister basicsPersister = new BasicsPersister();
    Document document = DocumentHelper.createDocument(DocumentHelper.createElement("Note")); //$NON-NLS-1$
    basicsPersister.save(itemData, document.getRootElement());
    IFile file = editorInput.getFile();
    try {
      String documentContent = DocumentUtilities.asString(document);
      ByteArrayInputStream source = new ByteArrayInputStream(documentContent.getBytes());
      file.setContents(source, true, true, new NullProgressMonitor());
      firePropertyChange(PROP_DIRTY);
    }
    catch (Exception e) {
      // TODO Fehlerhandlin
      e.printStackTrace();
    }
  }

  @Override
  public void doSaveAs() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void init(IEditorSite site, IEditorInput input) throws PartInitException {
    try {
      IStorageEditorInput storageInput = (IStorageEditorInput) input;
      Document xmlDocument = DocumentUtilities.read(storageInput.getStorage().getContents());
      BasicsPersister basicsPersister = new BasicsPersister();
      itemData = new BasicItemData();
      basicsPersister.load(xmlDocument.getRootElement(), itemData);
      itemData.addDirtyListener(new IChangeListener() {
        public void changeOccured() {
          firePropertyChange(PROP_DIRTY);
        }
      });
      setSite(site);
      setInput(input);
      setPartName(input.getName());
    }
    catch (Exception e) {
      throw new PartInitException("Error initializing styled text editor.", e); //$NON-NLS-1$
    }
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
    Text nameText = new Text(parent, SWT.SINGLE | SWT.BORDER);
    nameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    nameText.setText(itemData.getDescription().getName().getText());
    Label contentLabel = new Label(parent, SWT.LEFT);
    contentLabel.setText("Content:"); //$NON-NLS-1$
    contentLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false));
    contentComposite = new StyledText(parent, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP | SWT.BORDER);
    contentComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
    updateContent();
    getSite().setSelectionProvider(new StyledTextSelectionProvider(contentComposite));
    contentComposite.addExtendedModifyListener(new ExtendedModifyListener() {
      public void modifyText(ExtendedModifyEvent event) {
        String replacedText = event.replacedText;
        int index = event.start;
        String newText = contentComposite.getTextRange(index, event.length);
        IStyledTextualDescription styledText = getStyledDescription();
        styledText.replaceText(index, replacedText.length(), newText);
      }
    });
    getStyledDescription().addTextChangedListener(new IStyledTextChangeListener() {
      public void textChanged(ITextPart[] newParts) {
        updateContent();
      }
    });
  }

  private IStyledTextualDescription getStyledDescription() {
    return itemData.getDescription().getContent();
  }

  private void updateContent() {
    String newText = getStyledDescription().getText();
    if (!newText.equals(contentComposite.getText())) {
      contentComposite.setText(newText);
    }
    contentComposite.setStyleRanges(createStyleRanges());
  }

  private StyleRange[] createStyleRanges() {
    final StyleRangeFactory styleRangeFactory = new StyleRangeFactory();
    final int[] startIndex = new int[] { 0 };
    StyleRange[] styleRanges = ArrayUtilities.transform(
        getStyledDescription().getTextParts(),
        StyleRange.class,
        new ITransformer<ITextPart, StyleRange>() {
          public StyleRange transform(ITextPart textPart) {
            int length = textPart.getText().length();
            ITextFormat format = textPart.getFormat();
            StyleRange styleRange = styleRangeFactory.createStyleRange(startIndex[0], length, format);
            startIndex[0] += length;
            return styleRange;
          }
        });
    return styleRanges;
  }

  @Override
  public void setFocus() {
    contentComposite.setFocus();
  }

  public void modifySelection(ITextModification modification) {
    // TODO Auto-generated method stub
  }
}