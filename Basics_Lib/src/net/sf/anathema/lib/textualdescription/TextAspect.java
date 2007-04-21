package net.sf.anathema.lib.textualdescription;

import net.disy.commons.core.text.font.FontStyle;

public enum TextAspect {

  Bold {
    @Override
    public boolean isDominant(ITextFormat format) {
      return format.getFontStyle().isBold();
    }

    @Override
    public ITextFormat deriveFormat(ITextFormat input, boolean isDominant) {
      FontStyle derivedStyle;
      if (isDominant) {
        derivedStyle = input.getFontStyle().isItalic() ? FontStyle.ITALIC : FontStyle.PLAIN;
      }
      else {
        derivedStyle = input.getFontStyle().isItalic() ? FontStyle.BOLD_ITALIC : FontStyle.BOLD;
      }
      return TextFormat.deriveFormat(input, derivedStyle);
    }

  },
  Italic {
    @Override
    public boolean isDominant(ITextFormat format) {
      return format.getFontStyle().isItalic();
    }

    @Override
    public ITextFormat deriveFormat(ITextFormat input, boolean isDominant) {
      FontStyle derivedStyle;
      if (isDominant) {
        derivedStyle = input.getFontStyle().isBold() ? FontStyle.BOLD : FontStyle.PLAIN;
      }
      else {
        derivedStyle = input.getFontStyle().isBold() ? FontStyle.BOLD_ITALIC : FontStyle.ITALIC;
      }
      return TextFormat.deriveFormat(input, derivedStyle);
    }
  };

  public abstract boolean isDominant(ITextFormat format);

  public abstract ITextFormat deriveFormat(ITextFormat input, boolean isDominant);
}