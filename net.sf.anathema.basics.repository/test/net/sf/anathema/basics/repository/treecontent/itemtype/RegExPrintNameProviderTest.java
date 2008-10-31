package net.sf.anathema.basics.repository.treecontent.itemtype;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.junit.Test;

public class RegExPrintNameProviderTest {

  @Test
  public void fallbackNameIsTakenWhenFileExistsButContentDoesNotMatch() throws Exception {
    RegExPrintNameProvider nameProvider = new RegExPrintNameProvider("mySpecialFallbackName"); //$NON-NLS-1$
    InputStream inputStream = new ByteArrayInputStream(new byte[0]);
    assertEquals("mySpecialFallbackName", nameProvider.getPrintName(createExistingFile(inputStream))); //$NON-NLS-1$
  }

  @Test
  public void fallbackNameIsTakenIfFileNotExists() throws Exception {
    RegExPrintNameProvider nameProvider = new RegExPrintNameProvider("mySpecialFallbackName"); //$NON-NLS-1$
    assertEquals("mySpecialFallbackName", nameProvider.getPrintName(createNonExistingFile())); //$NON-NLS-1$
  }

  @Test
  public void fallbackNameIsTakenForNullFile() throws Exception {
    RegExPrintNameProvider nameProvider = new RegExPrintNameProvider("mySpecialFallbackName"); //$NON-NLS-1$
    assertEquals("mySpecialFallbackName", nameProvider.getPrintName(null)); //$NON-NLS-1$

  }

  private IFile createNonExistingFile() {
    IFile file = EasyMock.createMock(IFile.class);
    EasyMock.expect(file.exists()).andReturn(false).anyTimes();
    EasyMock.replay(file);
    return file;
  }

  private IFile createExistingFile(InputStream inputStream) throws CoreException {
    IFile file = EasyMock.createMock(IFile.class);
    EasyMock.expect(file.exists()).andReturn(true).anyTimes();
    EasyMock.expect(file.getContents()).andReturn(inputStream).anyTimes();
    EasyMock.replay(file);
    return file;
  }
}