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
  public int hashCode() {
    return ObjectUtilities.getHashCode(text);
  }

  @Override
  public String toString() {
    return "[" + text + ", " + format + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

  @Override
  public ITextPart[] split(int offset, int length) {
    Ensure.ensureArgumentFalse("Offset must be at least 0.", offset < 0); //$NON-NLS-1$
    Ensure.ensureArgumentFalse("Length must lie within text length.", length > getLength()); //$NON-NLS-1$
    if (offset == 0 && (length == getLength() || length == 0)) {
      return new ITextPart[] { this };
    }
    if (offset > 0 && length == getLength()) {
      return new ITextPart[] {
          new TextPart(getText().substring(0, offset), format),
          new TextPart(getText().substring(offset, getLength()), format) };
    }
    if (offset == 0 && length < getLength()) {
      return new ITextPart[] {
          new TextPart(getText().substring(0, length), format),
          new TextPart(getText().substring(length, getLength()), format) };
    }
    return new ITextPart[] {
        new TextPart(getText().substring(0, offset), format),
        new TextPart(getText().substring(offset, length), format),
        new TextPart(getText().substring(length, getLength()), format) };
  }

  public int getLength() {
    return text == null ? 0 : getText().length();
  }
}