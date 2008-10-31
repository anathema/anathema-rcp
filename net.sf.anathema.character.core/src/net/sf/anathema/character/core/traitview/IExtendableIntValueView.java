package net.sf.anathema.character.core.traitview;

import net.sf.anathema.lib.ui.IIntValueView;

public interface IExtendableIntValueView extends IIntValueView {

  public void addPainter(IIntValuePainter painter);
}