package net.sf.anathema.rcp;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

  private static final String PERSPECTIVE_ID = "net.sf.anathema.rcp.AnathemaPerspective"; //$NON-NLS-1$

  @Override
  public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
    return new ApplicationWorkbenchWindowAdvisor(configurer);
  }

  @Override
  public void initialize(IWorkbenchConfigurer configurer) {
    PlatformUI.getPreferenceStore().setValue(
        org.eclipse.ui.IWorkbenchPreferenceConstants.SHOW_PROGRESS_ON_STARTUP,
        true);
  }

  @Override
  public String getInitialWindowPerspectiveId() {
    return PERSPECTIVE_ID;
  }
}