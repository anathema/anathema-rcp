package editor.styledtext.editors;

import net.sf.anathema.framework.item.data.IBasicItemData;

import org.eclipse.ui.IEditorPart;

public interface IStyledTextEditor extends IEditorPart {

  public void modifySelection(ITextModification modification);

  public IBasicItemData getItemData();
}