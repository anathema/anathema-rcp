package net.sf.anathema.charms.view.tooltipp;

public class StringBuilder {

  private final String separator;
  private String result = ""; //$NON-NLS-1$

  public StringBuilder(String separator) {
    this.separator = separator;
  }

  public void add(String value) {
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