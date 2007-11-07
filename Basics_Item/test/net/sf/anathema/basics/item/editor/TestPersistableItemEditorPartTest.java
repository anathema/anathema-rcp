package net.sf.anathema.basics.item.editor;

import static org.junit.Assert.*;
import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.IPersistableEditorInput;

import org.easymock.EasyMock;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorSite;
import org.junit.Test;

public class TestPersistableItemEditorPartTest {

  @Test
  public void togglesItemCreationFlagWhenItemsAreCreated() throws Exception {
    TestPersistableItemEditorPart editorPart = new TestPersistableItemEditorPart();
    editorPart.createPartControl(new Shell());
    assertTrue(editorPart.isItemPartCreated());
  }

  @Test
  public void togglesInitFlagWhenItemsAreCreated() throws Exception {
    TestPersistableItemEditorPart editorPart = new TestPersistableItemEditorPart();
    IEditorSite site = EasyMock.createNiceMock(IEditorSite.class);
    IPersistableEditorInput<IItem> input = createNonErrorInput();
    editorPart.init(site, input);
    assertTrue(editorPart.isInitedForItem());
  }

  @Test
  public void togglesFocusIsSetForItemOnSetFocus() throws Exception {
    TestPersistableItemEditorPart editorPart = new TestPersistableItemEditorPart();
    IEditorSite site = EasyMock.createNiceMock(IEditorSite.class);
    editorPart.init(site, createNonErrorInput());
    editorPart.setFocus();
    assertTrue(editorPart.isFocusIsSetForItem());
  }

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
}