package net.sf.anathema.basics.jface;

import static org.junit.Assert.*;

import java.net.URL;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IPersistableElement;
import org.junit.Before;
import org.junit.Test;

public class FileEditorInput_PersistableTest {

  private boolean called;
  private IFile file;

  private class TestFileEditorInput extends FileEditorInput {

    public TestFileEditorInput(IFile file, URL imageUrl) {
      super(file, imageUrl);
    }

    @Override
    protected IPersistableElement getPersistableInternal() {
      called = true;
      return super.getPersistableInternal();
    }
  }

  @Before
  public void createFile() {
    file = EasyMock.createMock(IFile.class);
  }

  @Test
  public void retrievesInternalPersistable() throws Exception {
    EasyMock.expect(file.exists()).andReturn(true);
    EasyMock.replay(file);
    FileEditorInput input = new TestFileEditorInput(file, null);
    input.getPersistable();
    assertTrue(called);
  }

  @Test
  public void doesNotRetrieveInternalPersistableIfFileDoesNotExist() throws Exception {
    EasyMock.expect(file.exists()).andReturn(false);
    EasyMock.replay(file);
    FileEditorInput input = new TestFileEditorInput(file, null);
    input.getPersistable();
    assertFalse(called);
  }
}