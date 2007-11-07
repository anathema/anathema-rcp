package net.sf.anathema.character.core.editors;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IMemento;
import org.junit.Before;
import org.junit.Test;

public class ModelPersistableFactoryTest {

  private ModelPersistableFactory factory;
  private IContainer characterFolder;

  @Before
  public void createFactory() {
    characterFolder = EasyMock.createNiceMock(IContainer.class);
    IFile file = createFile(characterFolder);
    IContainer root = createContainerWithFileAtArbitraryPath(file);
    this.factory = new ModelPersistableFactory(root);
  }

  private IContainer createContainerWithFileAtArbitraryPath(IFile file) {
    IContainer root = EasyMock.createNiceMock(IContainer.class);
    EasyMock.expect(root.getFile(EasyMock.isA(IPath.class))).andStubReturn(file);
    EasyMock.replay(root);
    return root;
  }

  private IFile createFile(IContainer parent) {
    IFile file = EasyMock.createNiceMock(IFile.class);
    EasyMock.expect(file.getParent()).andStubReturn(parent);
    EasyMock.replay(file);
    return file;
  }

  @Test
  public void returnsMessageEditorInputForNonExistingFolder() throws Exception {
    EasyMock.expect(characterFolder.exists()).andStubReturn(false);
    EasyMock.replay(characterFolder);
    IMemento memento = EasyMock.createNiceMock(IMemento.class);
    EasyMock.expect(memento.getString(EasyMock.isA(String.class))).andReturn("karzenfuﬂ"); //$NON-NLS-1$
    EasyMock.replay(memento);
    IAdaptable adaptable = factory.createElement(memento);
    assertTrue(adaptable instanceof MessageEditorInput);
  }
}