package net.sf.anathema.basics.repository.treecontent.itemtype;

import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.basics.repository.RepositoryPlugin;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;

public class RegExPrintNameProvider implements IPrintNameProvider {

  private static final Pattern PRINT_NAME_PATTERN = Pattern.compile("<Name><!\\[CDATA\\[(.*)\\]\\]></Name>"); //$NON-NLS-1$
  private final String fallbackName;

  public RegExPrintNameProvider() {
    this(null);
  }

  public RegExPrintNameProvider(String fallbackName) {
    this.fallbackName = fallbackName;
  }

  // TODO Problem mit unbenannten Serienfiles!?
  public String getPrintName(IFile file) {
    InputStreamReader reader = null;
    try {
      if (!file.exists()) {
        return getFallbackName();
      }
      reader = new InputStreamReader(file.getContents());
      String content = IOUtilities.readString(reader);
      Matcher printNameMatcher = PRINT_NAME_PATTERN.matcher(content);
      if (!printNameMatcher.find()) {
        return getFallbackName();
      }
      return printNameMatcher.group(1);
    }
    catch (Exception e) {
      String message = Messages.PrintNameProvider_errorReadingDisplayNameMessage;
      RepositoryPlugin.getDefaultInstance().log(IStatus.ERROR, message, e);
      return file.getName();
    }
    finally {
      IOUtilities.close(reader);
    }
  }

  private String getFallbackName() {
    if (fallbackName != null) {
      return fallbackName;
    }
    throw new IllegalStateException("Illegal resource format: No display name defined."); //$NON-NLS-1$
  }
}