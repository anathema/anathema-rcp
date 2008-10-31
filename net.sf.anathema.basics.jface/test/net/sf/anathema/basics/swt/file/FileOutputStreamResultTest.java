package net.sf.anathema.basics.swt.file;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class FileOutputStreamResultTest {

  @Test
  public void deleteResultDeletesFile() throws Exception {
    File file = File.createTempFile("test", ".pdf"); //$NON-NLS-1$ //$NON-NLS-2$
    new FileOutputStreamResult(file).deleteResult();
    assertFalse(file.exists());
  }

  @Test
  public void throwsNoExceptionOnDeletionOfNullFile() throws Exception {
    new FileOutputStreamResult(null).deleteResult();
  }
}