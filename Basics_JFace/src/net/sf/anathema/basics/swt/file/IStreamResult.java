package net.sf.anathema.basics.swt.file;

import java.io.IOException;
import java.io.OutputStream;

public interface IStreamResult {

  public OutputStream createStream() throws IOException;

  public void openResult();

  public void deleteResult();
}