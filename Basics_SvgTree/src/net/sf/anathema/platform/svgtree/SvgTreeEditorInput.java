package net.sf.anathema.platform.svgtree;

import org.dom4j.Document;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class SvgTreeEditorInput implements IEditorInput {

  private final Document document;

  public SvgTreeEditorInput(Document document) {
    this.document = document;
  }

  public boolean exists() {
    return false;
  }

  public ImageDescriptor getImageDescriptor() {
    return null;
  }

  public String getName() {
    return "Pff";
  }

  public IPersistableElement getPersistable() {
    return null;
  }

  public String getToolTipText() {
    return "Tooltipp";
  }

  public Object getAdapter(Class adapter) {
    return null;
  }

  public Document getDocument() {
    return document;
  }
}