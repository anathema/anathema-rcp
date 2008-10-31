package net.sf.anathema.basics.repository.input.internal;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.resource.fake.ResourceObjectMother;
import net.sf.anathema.basics.item.editor.ErrorMessageEditorInput;
import net.sf.anathema.basics.repository.fake.MementoObjectMother;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.ui.IMemento;
import org.junit.Test;

public class FileItemEditorInputFactoryTest {

  @Test
  public void returnsErrorInputForNonExistingFile() throws Exception {
    IFile file = createFileThatNotExistsAfterRefresh();
    IContainer root = ResourceObjectMother.createContainerWithFileAtArbitraryPath(file);
    FileItemEditorInputFactory factory = new FileItemEditorInputFactory(root);
    IMemento memento = MementoObjectMother.createStringReturningMemento();
    assertTrue(factory.createElement(memento) instanceof ErrorMessageEditorInput);
  }

  public static IFile createFileThatNotExistsAfterRefresh() throws Exception {
    IFile resource = EasyMock.createStrictMock(IFile.class);
    resource.refreshLocal(IResource.DEPTH_ZERO, null);
    EasyMock.expect(resource.exists()).andReturn(false);
    EasyMock.expect(resource.getProjectRelativePath()).andReturn(null);
    EasyMock.replay(resource);
    return resource;
  }
}