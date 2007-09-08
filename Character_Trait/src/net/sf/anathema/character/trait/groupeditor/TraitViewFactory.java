package net.sf.anathema.character.trait.groupeditor;

import net.disy.commons.core.model.listener.IChangeListener;
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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

public class TraitViewFactory {

  private final class EnabledUpdateListener implements IChangeListener {
    private final Button favoredButton;
    private final IDisplayTrait trait;

    private EnabledUpdateListener(Button favoredButton, IDisplayTrait trait) {
      this.favoredButton = favoredButton;
      this.trait = trait;
      favoredButton.setEnabled(trait.getFavorization().isFavorable());
    }

    @Override
    public void stateChanged() {
      favoredButton.setEnabled(trait.getFavorization().isFavorable());
    }
  }

  private static final class FavorizationButtonChangeListener implements Listener {
    private final IDisplayTrait trait;
    private final Button button;

    private FavorizationButtonChangeListener(Button button, IDisplayTrait trait) {
      this.button = button;
      this.trait = trait;
    }

    @Override
    public void handleEvent(Event event) {
      button.setSelection(!button.getSelection());
      trait.getFavorization().toggleFavored();
    }
  }

  private static final class FavorizationModelListener implements IChangeListener {
    private final Button favoredButton;
    private final IDisplayTrait trait;
    private final Image passiveImage;
    private final Image activeImage;

    private FavorizationModelListener(Button favoredButton, IDisplayTrait trait, Image passiveImage, Image activeImage) {
      this.favoredButton = favoredButton;
      this.trait = trait;
      this.passiveImage = passiveImage;
      this.activeImage = activeImage;
      stateChanged();
    }

    @Override
    public void stateChanged() {
      favoredButton.setSelection(trait.getFavorization().isFavored());
      favoredButton.setImage(trait.getFavorization().isFavored() ? activeImage : passiveImage);
    }
  }

  public void addTo(
      Composite parent,
      Image passiveImage,
      Image activeImage,
      Image surplusImage,
      String text,
      final IDisplayTrait trait) {
    final Button favoredButton = new Button(parent, SWT.TOGGLE);
    trait.getFavorization().addFavorableChangeListener(new EnabledUpdateListener(favoredButton, trait));
    trait.getFavorization().addFavoredChangeListener(new FavorizationModelListener(favoredButton, trait, passiveImage, activeImage));
    favoredButton.addListener(SWT.MouseUp, new FavorizationButtonChangeListener(favoredButton, trait));
    createLabel(parent, GridDataFactory.createIndentData(5)).setText(text);
    final IIntValueView view = new CanvasIntValueDisplay(
        parent,
        passiveImage,
        activeImage,
        surplusImage, trait.getMaximalValue());
    new TraitPresenter().initPresentation(trait, view);
  }

  private Label createLabel(Composite parent, GridData data) {
    Label label = new Label(parent, SWT.NULL);
    label.setLayoutData(data);
    return label;
  }
}