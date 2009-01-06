package net.sf.anathema.charms.xml.data;

public class XmlCostText {

  private final String text;

  public XmlCostText(String text) {
    this.text = text;
  }

  public boolean isBase() {
    return text == null || isCombined() || isOrMore();
  }

  public boolean isLinear() {
    return text != null && text.indexOf(" per ") > -1;
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
    String unitMarker = " per ";
    int unitMarkerIndex = text.indexOf(unitMarker);
    return text.substring(unitMarkerIndex + unitMarker.length()).trim();
  }

  private boolean isCombined() {
    return text != null && text.startsWith("+");
  }
}