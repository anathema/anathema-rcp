package net.sf.anathema.lib.textualdescription;

public interface IStyledTextView {

  public void setFocus();

  public void setContent(String newText, ITextPart[] parts);

  public void addTextExchangeListener(ITextExchangeListener listener);
}