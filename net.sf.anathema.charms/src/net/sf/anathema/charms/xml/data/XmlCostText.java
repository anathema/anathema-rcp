package net.sf.anathema.charms.xml.data;

public class XmlCostText {

  private static final String START_PER = "per ";
  private static final String WHITESPACE_PER = " per ";
  private final String text;

  public XmlCostText(String text) {
    this.text = text;
  }

  public boolean isBase() {
    return text == null || isCombined() || isOrMore();
  }

  public boolean isLinear() {
    return text != null && getUnitMarkerIndex() > -1;
  }

  public int getLinearAmout(int baseAmount) {
    if (isCombined()) {
      byte firstNumber = text.substring(1).trim().getBytes()[0];
      return Integer.valueOf(new String(new byte[] { firstNumber }));
    }
    return baseAmount;
  }

  public boolean isOrMore() {
    return text != null && text.toLowerCase().equals("or more");
  }

  public String getLinearUnit() {
    int unitMarkerIndex = getUnitMarkerIndex();
    return text.substring(unitMarkerIndex).trim();
  }

  private int getUnitMarkerIndex() {
    if (text.startsWith(START_PER)) {
      return 4;
    }
    int index = text.indexOf(WHITESPACE_PER);
    if (index == -1) {
      return -1;
    }
    return index + WHITESPACE_PER.length();
  }

  private boolean isCombined() {
    return text != null && text.startsWith("+");
  }
}