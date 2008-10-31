package net.sf.anathema.lib.textualdescription;

public interface IStyledTextualDescription extends ITextualDescription, ITextAspectToggle {

  public void setText(ITextPart... textParts);

  public ITextPart[] getTextParts();

  public void addTextChangedListener(IStyledTextChangeListener listener);

  public void removeTextChangedListener(IStyledTextChangeListener listener);

  public void replaceText(int offset, int length, String newText);

  public boolean isDominant(TextAspect aspect, int offset, int length);
}