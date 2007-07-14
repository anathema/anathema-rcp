package net.sf.anathema.rcp;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

  public void createInitialLayout(IPageLayout layout) {
    String editorArea = layout.getEditorArea();
    layout.setEditorAreaVisible(true);
    layout.setFixed(false);
    // TODO Konstante ID nicht mehrfach deklarieren. Abhängigkeit dennoch vermeiden!
    // TODO Verschiedene Perspektiven bereistellen
    String id = "net.sf.anathema.basics.repositoryview"; //$NON-NLS-1$
    layout.addShowViewShortcut(id);
    layout.addStandaloneView(id, true, IPageLayout.LEFT, 0.25f, editorArea);
    layout.addStandaloneView("net.sf.anathema.character.experiencepoints", true, IPageLayout.BOTTOM, 0.8f, editorArea); //$NON-NLS-1$
  }
}