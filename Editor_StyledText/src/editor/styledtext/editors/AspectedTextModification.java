package editor.styledtext.editors;


import net.sf.anathema.lib.textualdescription.IStyledTextualDescription;
import net.sf.anathema.lib.textualdescription.TextAspect;

public class AspectedTextModification implements ITextModification {
  
  private final TextAspect aspect;

  public AspectedTextModification(TextAspect aspect) {
    this.aspect = aspect;
  }

  @Override
  public void perform(IStyledTextualDescription content, int offset, int length) {
    content.toggleAspect(aspect, offset, length);
  }

  @Override
  public boolean isActive(IStyledTextualDescription description, int offset, int length) {
    return description.isDominant(aspect, offset, length);
  }
}