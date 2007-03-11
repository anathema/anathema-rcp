package editor.styledtext.editors;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.lib.textualdescription.IStyledTextualDescription;
import net.sf.anathema.lib.textualdescription.ITextFormat;

import org.eclipse.jface.text.TextSelection;

public abstract class AbstractTextModification implements ITextModification {

  protected abstract boolean isActive(ITextFormat format);

  @Override
  public final boolean isActive(TextSelection selection, IStyledTextualDescription description) {
    return description.containsFormat(selection.getOffset(), selection.getLength(), new IPredicate<ITextFormat>() {
      @Override
      public boolean evaluate(ITextFormat format) {
        return isActive(format);
      }
    });
  }
}