package net.sf.anathema.basics.item.editor;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorSite;
import org.junit.Before;
import org.junit.Test;

public class AbstractPersistableItemEditorPartWithErrorInputTest {

  private TestPersistableItemEditorPart editor;
  private IEditorSite site;
  private ErrorMessageEditorInput errorInput;

  @Before
  public void initTestEditorWithErrorInput() throws Exception {
    this.editor = new TestPersistableItemEditorPart();
    site = EasyMock.createNiceMock(IEditorSite.class);
    EasyMock.replay(site);
    errorInput = new ErrorMessageEditorInput("FEHLA"); //$NON-NLS-1$
    editor.init(site, errorInput);
  }

  @Test
  public void siteIsSet() throws Exception {
    assertSame(site, editor.getSite());
  }

  @Test
  public void inputIsSet() throws Exception {
    assertSame(errorInput, editor.getEditorInput());
  }

  @Test
  public void isNotDirtyWithErrorInput() throws Exception {
    assertFalse(editor.isDirty());
  }

  @Test
  public void noItemPartCreated() throws Exception {
    editor.createPartControl(new Shell());
    assertFalse(editor.isItemPartCreated());
  }
}