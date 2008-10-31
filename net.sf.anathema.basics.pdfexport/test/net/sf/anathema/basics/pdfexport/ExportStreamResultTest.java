package net.sf.anathema.basics.pdfexport;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.io.File;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.basics.importexport.IFileSelectionModel;

import org.junit.Test;

public class ExportStreamResultTest {

  @Test
  public void deleteResultDeletesFile() throws Exception {
    File file = File.createTempFile("test", ".pdf"); //$NON-NLS-1$ //$NON-NLS-2$
    createObjectUnderTest(file).deleteResult();
    assertFalse(file.exists());
  }

  @Test
  public void throwsNoExceptionOnDeletionOfNullResult() throws Exception {
    createObjectUnderTest(null).deleteResult();
  }

  @Test
  public void throwsNoExceptionOnOpenNullResult() throws Exception {
    createObjectUnderTest(null).openResult();
  }

  private ExportStreamResult createObjectUnderTest(File file) {
    IFileSelectionModel fileSelectionModel = createMock(IFileSelectionModel.class);
    expect(fileSelectionModel.getFile()).andReturn(file).anyTimes();
    expect(fileSelectionModel.isComplete()).andReturn(file != null).anyTimes();
    replay(fileSelectionModel);
    return new ExportStreamResult(fileSelectionModel, new BooleanModel(true));
  }
}