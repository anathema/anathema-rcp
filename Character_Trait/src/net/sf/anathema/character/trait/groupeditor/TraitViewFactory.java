package net.sf.anathema.character.trait.groupeditor;

import java.util.List;

import net.sf.anathema.basics.swt.layout.GridDataFactory;
import net.sf.anathema.character.core.traitview.CanvasIntValueDisplay;
import net.sf.anathema.character.core.traitview.IExtendableIntValueView;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.character.trait.interactive.TraitPresenter;
import net.sf.anathema.character.trait.status.TraitStatusImageProviderContainer;

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

  public IExtendableIntValueView create(String text, final IInteractiveTrait trait) {
    final Button favoredButton = new Button(parent, SWT.TOGGLE);
    initListening(trait, favoredButton);
    createLabel(GridDataFactory.createIndentData(5)).setText(text);
    final CanvasIntValueDisplay view = new CanvasIntValueDisplay(
        parent,
        passiveImage,
        activeImage,
        trait.getMaximalValue());
    new TraitPresenter().initPresentation(trait, view);
    return view;
  }

  private void initListening(final IInteractiveTrait trait, final Button favoredButton) {
    trait.getFavorization().addFavorableChangeListener(new EnabledUpdateListener(favoredButton, trait));
    List<ITraitStatusImageProvider> imageProvider = new TraitStatusImageProviderContainer().getImageProvider();
    imageProvider.add(new FavoredTraitStatusImageProvider(activeImage));
    IImageProvider favorizationImageProvider = new FavorizationImageProvider(trait, passiveImage, imageProvider);
    trait.getFavorization().addFavoredChangeListener(
        new FavorizationModelListener(favoredButton, trait, favorizationImageProvider));
    favoredButton.addListener(SWT.MouseUp, new FavorizationButtonChangeListener(favoredButton, trait));
  }

  private Label createLabel(GridData data) {
    Label label = new Label(parent, SWT.NULL);
    label.setLayoutData(data);
    return label;
  }
}