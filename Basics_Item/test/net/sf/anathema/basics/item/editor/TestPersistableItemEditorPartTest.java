package net.sf.anathema.basics.item.editor;

import static org.junit.Assert.*;
import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.IPersistableEditorInput;

import org.easymock.EasyMock;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorSite;
import org.junit.Before;
import org.junit.Test;

public class TestPersistableItemEditorPartTest {

  private TestPersistableItemEditorPart editorPart;

  @SuppressWarnings("unchecked")
  private IPersistableEditorInput<IItem> createNonErrorInput() {
    IPersistableEditorInput input = EasyMock.createNiceMock(IPersistableEditorInput.class);
    IItem item = EasyMock.createNiceMock(IItem.class);
    EasyMock.expect(input.getItem()).andReturn(item);
    ImageDescriptor descriptor = ImageDescriptor.getMissingImageDescriptor();
    EasyMock.expect(input.getImageDescriptor()).andReturn(descriptor);
    EasyMock.replay(input);
    return input;
  }

  @Before
  public void createEditorWithItemInput() throws Exception {
    editorPart = new TestPersistableItemEditorPart();
    editorPart.init(EasyMock.createNiceMock(IEditorSite.class), createNonErrorInput());
  }

  @Test
  public void togglesItemCreationFlagWhenItemsAreCreated() throws Exception {
    editorPart.createPartControl(new Shell());
    assertTrue(editorPart.isItemPartCreated());
  }

  @Test
  public void togglesInitFlagWhenItemsAreCreated() throws Exception {
    assertTrue(editorPart.isInitedForItem());
  }

  @Test
  public void togglesFocusIsSetForItemOnSetFocus() throws Exception {
    editorPart.setFocus();
    assertTrue(editorPart.isFocusIsSetForItem());
  }
}