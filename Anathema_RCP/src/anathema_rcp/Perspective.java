package anathema_rcp;

import net.sf.anathema.basics.repository.RepositoryView;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

  public void createInitialLayout(IPageLayout layout) {
    String editorArea = layout.getEditorArea();
    layout.setEditorAreaVisible(true);
    layout.setFixed(false);
    layout.addStandaloneView(RepositoryView.ID, true, IPageLayout.LEFT, 0.25f, editorArea);
  }
}