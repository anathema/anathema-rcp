package net.sf.anathema.platform.svgtree;

import java.awt.Frame;

import net.sf.anathema.platform.svgtree.view.SvgTreeView;

import org.dom4j.Document;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class SvgDocumentEditor extends EditorPart {

  private Document document;

  @Override
  public void doSave(IProgressMonitor monitor) {
    // nothing to do
  }

  @Override
  public void doSaveAs() {
    // nothing to do
  }

  @Override
  public void init(IEditorSite site, IEditorInput input) throws PartInitException {
    this.document = ((SvgTreeEditorInput) input).getDocument();
    setSite(site);
    setInput(input);
    setPartName("Pfff");
    setTitleToolTip("Title Tooltipp");
  }

  @Override
  public boolean isDirty() {
    return false;
  }

  @Override
  public boolean isSaveAsAllowed() {
    return false;
  }

  @Override
  public void createPartControl(Composite parent) {
    try {
      SvgTreeView treeView = new SvgTreeView(new TestTheWestTreeViewProperties());
      treeView.loadCascade(document);
      Frame frame = SWT_AWT.new_Frame(new Composite(parent, SWT.EMBEDDED));
      frame.add(treeView.getComponent());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setFocus() {
    // nothing to do
  }
}