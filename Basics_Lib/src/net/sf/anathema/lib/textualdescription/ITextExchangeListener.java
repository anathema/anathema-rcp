package net.sf.anathema.lib.textualdescription;

public interface ITextExchangeListener {

  public void textReplaced(int startIndex, int replacedTextLength, String newText);
}