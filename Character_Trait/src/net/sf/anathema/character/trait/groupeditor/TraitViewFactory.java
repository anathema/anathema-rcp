package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.basics.swt.layout.GridDataFactory;
import net.sf.anathema.character.core.traitview.CanvasIntValueDisplay;
import net.sf.anathema.character.core.traitview.IExtendableIntValueView;
import net.sf.anathema.character.trait.IDisplayTrait;
import net.sf.anathema.character.trait.TraitPresenter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class TraitViewFactory {

  private final Composite parent;
  private final Image passiveImage;
  private final Image activeImage;

  public TraitViewFactory(Composite parent, IIntViewImageProvider provider) {
    this.parent = parent;
    this.passiveImage = provider.createPassiveImage();
    this.activeImage = provider.createActiveImage();
  }

  public IExtendableIntValueView create(String text, final IDisplayTrait trait) {
    final Button favoredButton = new Button(parent, SWT.TOGGLE);
    initListening(trait, favoredButton);
    createLabel(GridDataFactory.createIndentData(5)).setText(text);
    final CanvasIntValueDisplay view = new CanvasIntValueDisplay(parent, passiveImage, activeImage, trait.getMaximalValue());
    new TraitPresenter().initPresentation(trait, view);
    return view;
  }

  private void initListening(final IDisplayTrait trait, final Button favoredButton) {
    trait.getFavorization().addFavorableChangeListener(new EnabledUpdateListener(favoredButton, trait));
    trait.getFavorization().addFavoredChangeListener(
        new FavorizationModelListener(favoredButton, trait, passiveImage, activeImage));
    favoredButton.addListener(SWT.MouseUp, new FavorizationButtonChangeListener(favoredButton, trait));
  }

  private Label createLabel(GridData data) {
    Label label = new Label(parent, SWT.NULL);
    label.setLayoutData(data);
    return label;
  }
}