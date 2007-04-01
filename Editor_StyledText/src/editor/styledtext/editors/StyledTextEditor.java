package editor.styledtext.editors;

import java.io.ByteArrayInputStream;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.basics.jface.IFileEditorInput;
import net.sf.anathema.basics.jface.IStorageEditorInput;
import net.sf.anathema.basics.jface.text.SimpleTextView;
import net.sf.anathema.basics.jface.text.StyledTextView;
import net.sf.anathema.framework.item.data.BasicItemData;
import net.sf.anathema.framework.item.data.BasicsPersister;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.textualdescription.IStyledTextualDescription;
import net.sf.anathema.lib.textualdescription.ITextView;
import net.sf.anathema.lib.textualdescription.ITextualDescription;
import net.sf.anathema.lib.textualdescription.StyledTextPresenter;
import net.sf.anathema.lib.textualdescription.TextualPresenter;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
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

public class StyledTextEditor extends EditorPart implements IStyledTextEditor {

  private BasicItemData itemData;
  private StyledTextView contentView;

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
      itemData = loadData(((IStorageEditorInput) input).getStorage());
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

  protected String getPartName(BasicItemData itemData) {
    String name = itemData.getDescription().getName().getText();
    return StringUtilities.isNullOrEmpty(name) ? "Unnamed" : name;
  }

  protected BasicItemData loadData(IStorage storage) throws PersistenceException, CoreException {
    Document xmlDocument = DocumentUtilities.read(storage.getContents());
    BasicsPersister basicsPersister = new BasicsPersister();
    BasicItemData newData = new BasicItemData();
    basicsPersister.load(xmlDocument.getRootElement(), newData);
    return newData;
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
    new TextualPresenter(nameView, nameModel).initPresentation();
    Label contentLabel = new Label(parent, SWT.LEFT);
    contentLabel.setText("Content:"); //$NON-NLS-1$
    contentLabel.setLayoutData(createLabelData());
    final IStyledTextualDescription contentDescription = itemData.getDescription().getContent();
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
    IStyledTextualDescription styledText = itemData.getDescription().getContent();
    modification.perform(styledText, selectionRange.x, selectionRange.y);
  }

  @Override
  public boolean isActiveFor(ITextModification modification) {
    Point selectionRange = contentView.getSelectionRange();
    IStyledTextualDescription styledText = itemData.getDescription().getContent();
    return modification.isActive(styledText, selectionRange.x, selectionRange.y);
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