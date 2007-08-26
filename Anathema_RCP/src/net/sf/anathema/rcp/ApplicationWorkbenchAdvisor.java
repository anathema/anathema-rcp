package net.sf.anathema.rcp;

import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

  private static final String PERSPECTIVE_ID = "net.sf.anathema.rcp.AnathemaPerspective"; //$NON-NLS-1$

  @Override
  public void initialize(IWorkbenchConfigurer configurer) {
    super.initialize(configurer);
    configurer.setSaveAndRestore(true);
  }

  @Override
  public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
    return new ApplicationWorkbenchWindowAdvisor(configurer);
  }

  @Override
  public String getInitialWindowPerspectiveId() {
    return PERSPECTIVE_ID;
  }
}