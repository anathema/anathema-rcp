package net.sf.anathema.character.freebies.configuration.internal;

public class FreebiesResult implements IFreebiesResult {
  
  final int credit;
  final int value;

  public FreebiesResult(int credit, int value) {
    this.credit = credit;
    this.value = value;
  }

  public String getCredit() {
    return String.valueOf(credit);
  }

  public String getPoints() {
    return String.valueOf(value);
  }

  public boolean isValid() {
    return getPoints() != null && getCredit() != null;
  }
}