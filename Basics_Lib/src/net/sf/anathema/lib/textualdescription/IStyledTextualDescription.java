package net.sf.anathema.lib.textualdescription;

import net.disy.commons.core.predicate.IPredicate;

public interface IStyledTextualDescription extends ITextualDescription {

  public void setText(ITextPart... textParts);

  public ITextPart[] getTextParts();

  public void addTextChangedListener(IStyledTextChangeListener listener);

  public void removeTextChangedListener(IStyledTextChangeListener listener);

  public void replaceText(int offset, int length, String newText);

  public boolean isFormatted(int offset, int length, IPredicate<ITextFormat> predicate);

  public void toggleFontStyle(int offset, int length, TextAspect aspect);

  public void toggleUnderline(int offset, int length);
}