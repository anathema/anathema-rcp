package net.sf.anathema.basics.swt.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;

public class FileOutputStreamResult implements IStreamResult {

  private final File file;

  public FileOutputStreamResult(File file) {
    this.file = file;
  }

  @Override
  public OutputStream createStream() throws IOException {
    if (file != null) {
      return new FileOutputStream(file);
    }
    return null;
  }

  @Override
  public void obenResult() {
    try {
      if (file != null) {
        BrowserControl.displayUrl(file.toURL());
      }
    }
    catch (MalformedURLException e) {
      // TODO Fehlerhandling
      e.printStackTrace();
    }
  }
}