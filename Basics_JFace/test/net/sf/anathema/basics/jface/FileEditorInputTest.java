package net.sf.anathema.basics.jface;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.junit.Test;

public class FileEditorInputTest {

  @Test
  public void adaptsSelfToIFileEditorInput() throws Exception {
    FileEditorInput input = new FileEditorInput(EasyMock.createMock(IFile.class), null);
    assertEquals(input, input.getAdapter(IFileEditorInput.class));
  }

  @Test
  public void adaptsFileToIResource() throws Exception {
    IFile file = EasyMock.createMock(IFile.class);
    assertEquals(file, new FileEditorInput(file, null).getAdapter(IResource.class));
  }
}