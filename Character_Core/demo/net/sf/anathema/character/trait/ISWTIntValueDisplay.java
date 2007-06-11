package net.sf.anathema.character.trait;

import org.eclipse.swt.widgets.Composite;

import net.sf.anathema.lib.ui.IIntValueView;

public interface ISWTIntValueDisplay extends IIntValueView {

  public Composite createComposite(Composite parent);
}