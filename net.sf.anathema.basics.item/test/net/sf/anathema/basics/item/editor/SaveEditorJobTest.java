package net.sf.anathema.basics.item.editor;

import net.sf.anathema.basics.item.IPersistableEditorInput;

import org.easymock.EasyMock;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SaveEditorJobTest {

  @org.junit.Test
  public void noExceptionWhenDisplayDisposed() throws Exception {
    IPersistableEditorInput< ? > input = EasyMock.createMock(IPersistableEditorInput.class);
    EasyMock.expect(input.getName()).andReturn("Hugo").anyTimes(); //$NON-NLS-1$
    NullProgressMonitor monitor = new NullProgressMonitor();
    EasyMock.expect(input.save(monitor)).andReturn(null);
    EasyMock.replay(input);
    Display display = new Shell().getDisplay();
    display.dispose();
    SaveEditorJob job = new SaveEditorJob(input, new FireDirtyRunnable(null), display);
    job.run(monitor);
  }
}
