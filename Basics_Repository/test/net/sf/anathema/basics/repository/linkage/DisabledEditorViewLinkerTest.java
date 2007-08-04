package net.sf.anathema.basics.repository.linkage;

import org.easymock.EasyMock;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IWorkbenchWindow;
import org.junit.Before;
import org.junit.Test;

public class DisabledEditorViewLinkerTest {
  private EditorViewLinker linker;
  private IWorkbenchWindow window;
  private ISelectionProvider provider;

  @Before
  public void createLinker() {
    window = EasyMock.createMock(IWorkbenchWindow.class);
    provider = EasyMock.createMock(ISelectionProvider.class);
    linker = new EditorViewLinker(window, provider);
  }

  @Test
  public void isInitiallyDisabled() throws Exception {
    EasyMock.replay(window, provider);
    linker.update();
    EasyMock.verify(window, provider);
  }
}
