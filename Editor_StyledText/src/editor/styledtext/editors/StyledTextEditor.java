package editor.styledtext.editors;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.basics.jface.IStorageEditorInput;
import net.sf.anathema.framework.item.data.BasicItemData;
import net.sf.anathema.framework.item.data.BasicsPersister;
import net.sf.anathema.lib.textualdescription.ITextFormat;
import net.sf.anathema.lib.textualdescription.ITextPart;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
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
      IStorageEditorInput fileInput = (IStorageEditorInput) input;
      Document xmlDocument = DocumentUtilities.read(fileInput.getStorage().getContents());
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
    StyledText contentComposite = new StyledText(parent, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP | SWT.BORDER);
    contentComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
    StyleRange[] styleRanges = createStyleRanges();
    contentComposite.setText(itemData.getDescription().getContent().getText());
    contentComposite.setStyleRanges(styleRanges);
  }

  private StyleRange[] createStyleRanges() {
    final StyleRangeFactory styleRangeFactory = new StyleRangeFactory();
    final int[] startIndex = new int[] { 0 };
    StyleRange[] styleRanges = ArrayUtilities.transform(
        itemData.getDescription().getContent().getTextParts(),
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
    // TODO Auto-generated method stub
  }
}