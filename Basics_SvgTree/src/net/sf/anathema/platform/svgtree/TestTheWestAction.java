package net.sf.anathema.platform.svgtree;

import net.sf.anathema.platform.svgtree.document.CascadeDocumentFactory;
import net.sf.anathema.platform.svgtree.graph.nodes.IRegularNode;
import net.sf.anathema.platform.svgtree.graph.nodes.IdentifiedRegularNode;

import org.dom4j.Document;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import anathema_rcp.IWindowAction;

public class TestTheWestAction extends Action implements IWindowAction {

  private IWorkbenchWindow window;

  public TestTheWestAction() {
    super("Test the West");
  }

  @Override
  public void run() {
    final CascadeDocumentFactory provider = new CascadeDocumentFactory();
    IRegularNode[] nodes = new IRegularNode[] { new IdentifiedRegularNode(new IRegularNode[0], "Hasäntum") }; //$NON-NLS-1$
    Document document = provider.createCascadeDocument(nodes, new TestTheWestProperties());
    IEditorInput input = new SvgTreeEditorInput(document);
    IEditorDescriptor defaultEditor = PlatformUI.getWorkbench().getEditorRegistry().findEditor("svgTreeEditor");
    try {
      System.err.println(defaultEditor.getClass());
      window.getPages()[0].openEditor(input, defaultEditor.getId());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void setWorkbenchWindow(IWorkbenchWindow window) {
    this.window = window;
  }
}