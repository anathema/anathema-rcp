package editor.styledtext.editors;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.lib.textualdescription.IStyledTextualDescription;
import net.sf.anathema.lib.textualdescription.ITextFormat;

public abstract class AbstractTextModification implements ITextModification {

  protected abstract boolean isActive(ITextFormat format);

  @Override
  public boolean isActive(IStyledTextualDescription description, int offset, int length) {
    return description.containsFormat(offset, length, new IPredicate<ITextFormat>() {
      @Override
      public boolean evaluate(ITextFormat format) {
        return isActive(format);
      }
    });
  }
}