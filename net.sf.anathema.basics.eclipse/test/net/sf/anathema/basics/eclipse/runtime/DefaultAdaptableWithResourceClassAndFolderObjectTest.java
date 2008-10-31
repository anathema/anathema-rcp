package net.sf.anathema.basics.eclipse.runtime;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.junit.Before;
import org.junit.Test;

public class DefaultAdaptableWithResourceClassAndFolderObjectTest {

  private DefaultAdaptable adaptable;
  private IFolder folder;

  @Before
  public void createEmptyAdaptable() {
    this.folder = EasyMock.createMock(IFolder.class);
    this.adaptable = new DefaultAdaptable();
    this.adaptable.set(IResource.class, new IProvider<IResource>() {
      @Override
      public IResource get() {
        return folder;
      }
    });
  }

  @Test
  public void objectClassIsNotAdapted() throws Exception {
    assertNull(adaptable.getAdapter(Object.class));
  }

  @Test
  public void resourceClassIsAdaptedWithFolderObject() throws Exception {
    assertSame(folder, adaptable.getAdapter(IResource.class));
  }

  @Test
  public void folderClassIsAdaptedWithFolderObject() throws Exception {
    assertSame(folder, adaptable.getAdapter(IFolder.class));
  }

  @Test
  public void fileClassIsNotAdapted() throws Exception {
    assertNull(adaptable.getAdapter(IFile.class));
  }

  @Test
  public void firstFittingAdaptableIsUsed() throws Exception {
    final IFile file = EasyMock.createMock(IFile.class);
    adaptable.set(IFile.class, new IProvider<IFile>() {
      @Override
      public IFile get() {
        return file;
      }
    });
    assertSame(folder, adaptable.getAdapter(IResource.class));
  }

  @Test
  public void specificClassIsPreferred() throws Exception {
    final IFolder otherFolder = EasyMock.createMock(IFolder.class);
    adaptable.set(IFolder.class, new IProvider<IFolder>() {
      @Override
      public IFolder get() {
        return otherFolder;
      }
    });
    assertSame(otherFolder, adaptable.getAdapter(IFolder.class));
  }
}