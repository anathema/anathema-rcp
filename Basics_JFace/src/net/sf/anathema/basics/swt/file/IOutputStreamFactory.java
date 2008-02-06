package net.sf.anathema.basics.swt.file;

import java.io.FileNotFoundException;

import org.eclipse.swt.widgets.Shell;

public interface IOutputStreamFactory {

  public IStreamResult create(Shell shell) throws FileNotFoundException;
}