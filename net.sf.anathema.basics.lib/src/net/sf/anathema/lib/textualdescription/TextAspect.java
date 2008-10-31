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
      FontStyle derivedStyle = FontStyle.getStyle(!isDominant, input.getFontStyle().isItalic());
      return TextFormat.deriveFormatWithFontStyle(input, derivedStyle);
    }

  },
  Italic {
    @Override
    public boolean isDominant(ITextFormat format) {
      return format.getFontStyle().isItalic();
    }

    @Override
    public ITextFormat deriveFormat(ITextFormat input, boolean isDominant) {
      FontStyle derivedStyle = FontStyle.getStyle(input.getFontStyle().isBold(), !isDominant);
      return TextFormat.deriveFormatWithFontStyle(input, derivedStyle);
    }
  },

  Underline {
    @Override
    public boolean isDominant(ITextFormat format) {
      return format.isUnderline();
    }

    @Override
    public ITextFormat deriveFormat(ITextFormat input, boolean isDominant) {
      return TextFormat.deriveFormatWithUnderline(input, !isDominant);
    }
  };

  public abstract boolean isDominant(ITextFormat format);

  public abstract ITextFormat deriveFormat(ITextFormat input, boolean isDominant);
}