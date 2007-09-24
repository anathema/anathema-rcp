package net.sf.anathema.basics.swt.file;

import java.io.FileNotFoundException;
import java.io.OutputStream;

import org.eclipse.swt.widgets.Shell;

public interface IOutputStreamFactory {

  public OutputStream create(Shell shell) throws FileNotFoundException;
}