package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.basics.swt.layout.GridDataFactory;
import net.sf.anathema.character.core.traitview.CanvasIntValueDisplay;
import net.sf.anathema.character.trait.IDisplayTrait;
import net.sf.anathema.character.trait.TraitPresenter;
import net.sf.anathema.lib.ui.IIntValueView;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class TraitViewFactory {

  public void addTo(
      Composite parent,
      Image passiveImage,
      Image activeImage,
      Image surplusImage,
      String text,
      final IDisplayTrait trait) {
    final Button favoredButton = new Button(parent, SWT.TOGGLE);
    trait.getFavorization().addFavorableChangeListener(new EnabledUpdateListener(favoredButton, trait));
    trait.getFavorization().addFavoredChangeListener(
        new FavorizationModelListener(favoredButton, trait, passiveImage, activeImage));
    favoredButton.addListener(SWT.MouseUp, new FavorizationButtonChangeListener(favoredButton, trait));
    createLabel(parent, GridDataFactory.createIndentData(5)).setText(text);
    final IIntValueView view = new CanvasIntValueDisplay(
        parent,
        passiveImage,
        activeImage,
        surplusImage,
        trait.getMaximalValue());
    new TraitPresenter().initPresentation(trait, view);
  }

  private Label createLabel(Composite parent, GridData data) {
    Label label = new Label(parent, SWT.NULL);
    label.setLayoutData(data);
    return label;
  }
}