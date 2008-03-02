package net.sf.anathema.editor.styledtext.jface;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.projection.ProjectionDocumentManager;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.ITokenScanner;

public class MasterTokenScanner implements ITokenScanner {

  private final IDocument master;
  private final ProjectionDocumentManager manager;

  public MasterTokenScanner(ProjectionDocumentManager manager, IDocument master) {
    this.manager = manager;
    this.master = master;
  }

  @Override
  public int getTokenLength() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getTokenOffset() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public IToken nextToken() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setRange(IDocument document, int offset, int length) {
    // TODO Auto-generated method stub

  }

}
