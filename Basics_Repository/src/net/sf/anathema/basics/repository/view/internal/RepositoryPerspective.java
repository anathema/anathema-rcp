package net.sf.anathema.basics.repository.view.internal;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class RepositoryPerspective implements IPerspectiveFactory {

  public void createInitialLayout(IPageLayout layout) {
    String editorArea = layout.getEditorArea();
    layout.setEditorAreaVisible(true);
    layout.setFixed(false);
    // TODO Constant: Repositoryview-ID
    String repositoryviewId = "net.sf.anathema.basics.repositoryview"; //$NON-NLS-1$
    layout.addShowViewShortcut(repositoryviewId);
    layout.addStandaloneView(repositoryviewId, true, IPageLayout.LEFT, 0.25f, editorArea);
  }
}