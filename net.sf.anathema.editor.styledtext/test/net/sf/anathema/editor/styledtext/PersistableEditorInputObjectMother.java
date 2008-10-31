package net.sf.anathema.editor.styledtext;

import static org.easymock.EasyMock.*;
import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.IPersistableEditorInput;

import org.eclipse.jface.resource.ImageDescriptor;

public class PersistableEditorInputObjectMother {

  @SuppressWarnings("unchecked")
  public static <M extends IItem> IPersistableEditorInput<M> create(M item, String name) {
    IPersistableEditorInput<M> input = createMock(IPersistableEditorInput.class);
    expect(input.getItem()).andReturn(item).anyTimes();
    expect(input.getName()).andReturn(name).anyTimes();
    expect(input.getImageDescriptor()).andReturn(ImageDescriptor.getMissingImageDescriptor());
    replay(input);
    return input;
  }
}