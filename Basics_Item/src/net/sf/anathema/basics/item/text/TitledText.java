package net.sf.anathema.basics.item.text;

import net.sf.anathema.lib.control.AggregatedChangeManagement;
import net.sf.anathema.lib.textualdescription.IStyledTextualDescription;
import net.sf.anathema.lib.textualdescription.ITextualDescription;
import net.sf.anathema.lib.textualdescription.SimpleTextualDescription;
import net.sf.anathema.lib.textualdescription.StyledTextualDescription;

public class TitledText extends AggregatedChangeManagement implements ITitledText {

  private final ITextualDescription name;
  private final IStyledTextualDescription content;

  public TitledText() {
    this(""); //$NON-NLS-1$
  }

  public TitledText(String initialName) {
    this.name = new SimpleTextualDescription(initialName);
    this.content = new StyledTextualDescription();
    setChangeManagments(name, content);
  }

  public ITextualDescription getName() {
    return name;
  }

  public IStyledTextualDescription getContent() {
    return content;
  }
}