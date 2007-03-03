package anathema_rcp;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import anathema_rcp.repository.RepositoryView;

public class Perspective implements IPerspectiveFactory {

  public void createInitialLayout(IPageLayout layout) {
    String editorArea = layout.getEditorArea();
    layout.setEditorAreaVisible(true);
    layout.setFixed(false);
    layout.addStandaloneView(RepositoryView.ID, true, IPageLayout.LEFT, 0.25f, editorArea);
  }
}