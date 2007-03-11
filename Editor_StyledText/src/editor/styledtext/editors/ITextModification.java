package editor.styledtext.editors;

import net.sf.anathema.lib.textualdescription.IStyledTextualDescription;

import org.eclipse.jface.text.TextSelection;

public interface ITextModification {

  public boolean isActive(TextSelection selection, IStyledTextualDescription description);
}