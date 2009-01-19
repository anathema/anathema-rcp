package net.sf.anathema.basics.repository.treecontent;

import static org.easymock.EasyMock.*;

import java.lang.reflect.InvocationTargetException;

import net.sf.anathema.basics.repository.fake.WorkbenchUiObjectMother;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.junit.Test;

public class ViewElementDeleterTest {

  /** For Case 127 */
  @Test
  public void insistsOnExecutionOnUiThread() throws Exception {
    ViewElementDeleter deleter = new ViewElementDeleter();
    IWorkbenchWindow window = createWorkbenchWindow();
    IWorkbenchPage page = WorkbenchUiObjectMother.createWorkbenchPage(window);
    deleter.setPage(page);
    replay(window);
    deleter.delete(null);
    verify(window);
  }

  private IWorkbenchWindow createWorkbenchWindow() throws InvocationTargetException, InterruptedException {
    IWorkbenchWindow window = createMock(IWorkbenchWindow.class);
    window.run(eq(false), anyBoolean(), isA(ViewElementDeleteRunnable.class));
    return window;
  }
}