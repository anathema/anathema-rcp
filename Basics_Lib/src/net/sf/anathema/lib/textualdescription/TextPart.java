package net.sf.anathema.lib.textualdescription;

import net.disy.commons.core.util.Ensure;
import net.disy.commons.core.util.ObjectUtilities;

public class TextPart implements ITextPart {

  private final ITextFormat format;
  private final String text;

  public TextPart(String text, ITextFormat format) {
    this.text = text;
    this.format = format;
  }

  public String getText() {
    return text;
  }

  public ITextFormat getFormat() {
    return format;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof TextPart)) {
      return false;
    }
    TextPart other = (TextPart) obj;
    return ObjectUtilities.equals(format, other.format) && ObjectUtilities.equals(text, other.text);
  }

  @Override
  public String toString() {
    return "[" + text + ", " + format + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

  @Override
  public ITextPart[] split(int offset) {
    Ensure.ensureArgumentFalse("offset must be at least 0.", offset < 0); //$NON-NLS-1$
    Ensure.ensureArgumentFalse("Offset must lie within text length.", offset > getLength()); //$NON-NLS-1$
    if (offset == getLength() || offset == 0) {
      return new ITextPart[] { this };
    }
    return new ITextPart[] {
        new TextPart(getText().substring(0, offset), format),
        new TextPart(getText().substring(offset), format) };
  }

  private int getLength() {
    return text == null ? 0 : getText().length();
  }
}