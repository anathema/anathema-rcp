package net.sf.anathema.lib.textualdescription;

public interface ITextPart {

  public String getText();

  public ITextFormat getFormat();

  public ITextPart[] split(int offset);
}