package editor.styledtext.editors;

import net.sf.anathema.framework.item.data.BasicItemData;
import net.sf.anathema.framework.item.data.BasicsPersister;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class StyledTextEditor extends EditorPart {
  private BasicItemData itemData;

  @Override
  public void doSave(IProgressMonitor monitor) {
    // TODO Auto-generated method stub

  }

  @Override
  public void doSaveAs() {
    // TODO Auto-generated method stub

  }

  @Override
  public void init(IEditorSite site, IEditorInput input) throws PartInitException {
    try {
      IFileEditorInput fileInput = (IFileEditorInput) input;
      Document xmlDocument = DocumentUtilities.read(fileInput.getFile().getContents());
      BasicsPersister basicsPersister = new BasicsPersister();
      itemData = new BasicItemData();
      basicsPersister.load(xmlDocument.getRootElement(), itemData);
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
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isSaveAsAllowed() {
    return false;
  }

  @Override
  public void createPartControl(Composite parent) {
    // Composite parent = new Composite(parent, SWT.NONE);
    parent.setLayout(new GridLayout(2, false));
    Label nameLabel = new Label(parent, SWT.LEFT);
    nameLabel.setText("Name:"); //$NON-NLS-1$
    Text nameText = new Text(parent, SWT.SINGLE | SWT.BORDER);
    nameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    nameText.setText(itemData.getDescription().getName().getText());
    Label contentLabel = new Label(parent, SWT.LEFT);
    contentLabel.setText("Content:"); //$NON-NLS-1$
    contentLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false));
    Text contentText = new Text(parent, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP | SWT.BORDER);
    contentText.setLayoutData(new GridData(GridData.FILL_BOTH));
    contentText.setText(itemData.getDescription().getContent().getText());
  }

  @Override
  public void setFocus() {
    // TODO Auto-generated method stub
  }
}