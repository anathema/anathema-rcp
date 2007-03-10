package editor.styledtext.editors;

import java.io.ByteArrayInputStream;

import net.sf.anathema.basics.jface.IFileEditorInput;
import net.sf.anathema.basics.jface.IStorageEditorInput;
import net.sf.anathema.basics.jface.selection.StyledTextSelectionProvider;
import net.sf.anathema.basics.jface.text.SimpleTextView;
import net.sf.anathema.basics.jface.text.StyledTextView;
import net.sf.anathema.framework.item.data.BasicItemData;
import net.sf.anathema.framework.item.data.BasicsPersister;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.textualdescription.IStyledTextChangeListener;
import net.sf.anathema.lib.textualdescription.IStyledTextView;
import net.sf.anathema.lib.textualdescription.IStyledTextualDescription;
import net.sf.anathema.lib.textualdescription.ITextExchangeListener;
import net.sf.anathema.lib.textualdescription.ITextPart;
import net.sf.anathema.lib.textualdescription.ITextView;
import net.sf.anathema.lib.textualdescription.ITextualDescription;
import net.sf.anathema.lib.textualdescription.TextualPresentation;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class StyledTextEditor extends EditorPart implements IStyledTextEditor {

  private BasicItemData itemData;
  private IStyledTextView contentView;

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
      itemData.setClean();
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
    nameLabel.setLayoutData(createLabelData());
    final ITextView nameView = new SimpleTextView(parent);
    final ITextualDescription nameModel = itemData.getDescription().getName();
    new TextualPresentation().initView(nameView, nameModel);
    Label contentLabel = new Label(parent, SWT.LEFT);
    contentLabel.setText("Content:"); //$NON-NLS-1$
    contentLabel.setLayoutData(createLabelData());
    final IStyledTextualDescription contentDescription = itemData.getDescription().getContent();
    contentView = new StyledTextView(parent);
    contentView.addTextExchangeListener(new ITextExchangeListener() {
      @Override
      public void textReplaced(int startIndex, int replacedTextLength, String newText) {
        contentDescription.replaceText(startIndex, replacedTextLength, newText);
      }
    });
    contentDescription.addTextChangedListener(new IStyledTextChangeListener() {
      public void textChanged(ITextPart[] newParts) {
        updateContent(contentView, contentDescription);
      }
    });
    updateContent(contentView, contentDescription);
    getSite().setSelectionProvider(new StyledTextSelectionProvider(contentComposite));
  }

  private GridData createLabelData() {
    return new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false);
  }

  private void updateContent(IStyledTextView view, IStyledTextualDescription description) {
    view.setContent(description.getText(), description.getTextParts());
  }

  @Override
  public void setFocus() {
    contentView.setFocus();
  }

  public void modifySelection(ITextModification modification) {
    // TODO Auto-generated method stub
  }
}