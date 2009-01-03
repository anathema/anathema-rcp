package net.sf.anathema.charms.view.tooltipp;

public class ConcatenateString {

  private final String separator;
  private String result = ""; //$NON-NLS-1$

  public static ConcatenateString CommaSeparated() {
    return new ConcatenateString(", "); //$NON-NLS-1$
  }

  public static ConcatenateString LineWrapping() {
    return new ConcatenateString("\n   "); //$NON-NLS-1$
  }

  public ConcatenateString(String separator) {
    this.separator = separator;
  }

  public void concatenate(String value) {
    if (value == null || value.isEmpty()) {
      return;
    }
    if (!result.isEmpty()) {
      result += separator;
    }
    result += value;
  }

  public String create(String fallback) {
    return result.isEmpty() ? fallback : result;
  }

  public String create() {
    return result;
  }
}