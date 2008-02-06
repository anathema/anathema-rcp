// Copyright (c) 2004 by disy Informationssysteme GmbH
package net.sf.anathema.basics.swt.file;

import java.io.IOException;

//NOT_PUBLISHED
public class WindowsUtilities {
  private static final String SYSTEM_PROPERTY_OS_NAME = "os.name"; //$NON-NLS-1$
  private static final String WINDOWS_NT_COMMAND = "cmd.exe /c "; //$NON-NLS-1$
  private static final String WINDOWS_9X_COMMAND = "command.com /c "; //$NON-NLS-1$

  public static boolean isWindowsNt() {
    final String os = getOsProperty();
    return (os.indexOf("nt") > -1) //$NON-NLS-1$
        || (os.indexOf("windows 2000") > -1) //$NON-NLS-1$
        || (os.indexOf("windows xp") > -1) //$NON-NLS-1$
        || (os.indexOf("windows 2003") > -1) //$NON-NLS-1$
        || (os.indexOf("vista") > -1); //$NON-NLS-1$
  }

  private static String getOsProperty() {
    return System.getProperty(SYSTEM_PROPERTY_OS_NAME).toLowerCase();
  }

  public static boolean isWindows9x() {
    return getOsProperty().indexOf("windows 9") > -1; //$NON-NLS-1$
  }

  public static boolean isWindows() {
    return isWindowsNt() || isWindows9x();
  }

  public static Process executeMsdosCommand(final String command) throws IOException {
    final Runtime runtime = Runtime.getRuntime();
    if (isWindowsNt()) {
      return runtime.exec(WINDOWS_NT_COMMAND + command);
    }
    else if (isWindows9x()) {
      return runtime.exec(WINDOWS_9X_COMMAND + command);
    }
    else {
      throw new IllegalStateException(Messages.WindowsUtilities_OnlyWindowsOperationSystem);
    }
  }
}
