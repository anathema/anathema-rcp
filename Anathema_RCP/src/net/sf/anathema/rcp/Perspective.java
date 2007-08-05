package net.sf.anathema.rcp;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

  public void createInitialLayout(IPageLayout layout) {
    String editorAreaReference = layout.getEditorArea();
    layout.setEditorAreaVisible(true);
    layout.setFixed(false);
    // TODO Konstante ID nicht mehrfach deklarieren. Abhängigkeit dennoch vermeiden!
    // TODO Verschiedene Perspektiven bereistellen
    String id = "net.sf.anathema.basics.repositoryview"; //$NON-NLS-1$
    layout.addShowViewShortcut(id);
    layout.addStandaloneView(id, true, IPageLayout.LEFT, 0.25f, editorAreaReference);
    IFolderLayout characterFolder = layout.createFolder("folder.character", IPageLayout.BOTTOM, 0.8f, editorAreaReference); //$NON-NLS-1$
    characterFolder.addView("net.sf.anathema.character.points"); //$NON-NLS-1$
    characterFolder.addView("net.sf.anathema.character.freebies"); //$NON-NLS-1$
  }
}