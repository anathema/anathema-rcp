package net.sf.anathema.character.core.traitview;

import org.eclipse.swt.widgets.Composite;

import net.sf.anathema.lib.ui.IIntValueView;

public interface ISWTIntValueDisplay extends IIntValueView {

  public Composite createComposite(Composite parent);
}