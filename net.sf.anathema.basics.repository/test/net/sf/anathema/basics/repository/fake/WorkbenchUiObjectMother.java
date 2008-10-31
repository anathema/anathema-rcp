package net.sf.anathema.basics.repository.fake;

import static org.easymock.EasyMock.*;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;

public class WorkbenchUiObjectMother {

  public static IWorkbenchPage createWorkbenchPage(IWorkbenchWindow window) {
    IWorkbenchPage page = createMock(IWorkbenchPage.class);
    expect(page.getWorkbenchWindow()).andReturn(window);
    replay(page);
    return page;
  }
}