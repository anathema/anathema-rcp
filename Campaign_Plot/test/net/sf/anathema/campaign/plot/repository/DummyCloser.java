package net.sf.anathema.campaign.plot.repository;

import org.eclipse.ui.IEditorPart;

public class DummyCloser implements IEditorCloser {

  private boolean closed;

  @Override
  public void close(IEditorPart editor) {
    this.closed = true;
  }

  public boolean isClosed() {
    return closed;
  }
}
