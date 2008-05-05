package net.sf.anathema.character.trait.groupeditor;

import java.util.List;

import net.sf.anathema.basics.swt.layout.GridDataFactory;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.traitview.CanvasIntValueDisplay;
import net.sf.anathema.character.core.traitview.IExtendableIntValueView;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.character.trait.interactive.TraitPresenter;
import net.sf.anathema.character.trait.status.ITraitStatusImageProvider;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class TraitViewFactory {

  private final Composite parent;
  private final Image passiveImage;
  private final Image activeImage;
  private final ICharacterId characterId;

  public TraitViewFactory(Composite parent, IIntViewImageProvider provider, ICharacterId characterId) {
    this.parent = parent;
    this.characterId = characterId;
    this.passiveImage = provider.createPassiveImage();
    this.activeImage = provider.createActiveImage();
  }

  public IExtendableIntValueView create(
      String text,
      FormToolkit toolkit,
      final IInteractiveTrait trait) {
    final Button favoredButton = toolkit.createButton(parent, null, SWT.TOGGLE);
    initListening(trait, favoredButton);
    Color background = toolkit.getColors().getBackground();
    createLabel(background, createGridData()).setText(text);
    final CanvasIntValueDisplay view = new CanvasIntValueDisplay(
        background,
        parent,
        passiveImage,
        activeImage,
        trait.getMaximalValue());
    new TraitPresenter().initPresentation(trait, view);
    return view;
  }

  private GridData createGridData() {
    GridData data = new GridData();
    data.horizontalIndent = 5;
    data.grabExcessHorizontalSpace = true;
    return data;
  }

  private void initListening(final IInteractiveTrait trait, final Button favoredButton) {
    trait.getFavorization().addFavorableChangeListener(new EnabledUpdateListener(favoredButton, trait));
    List<ITraitStatusImageProvider> imageProvider = new TraitStatusImageProviderContainer().getImageProvider();
    imageProvider.add(new FavoredTraitStatusImageProvider(activeImage));
    IImageProvider favorizationImageProvider = new FavorizationImageProvider(
        trait,
        passiveImage,
        imageProvider,
        characterId);
    FavorizationModelListener listener = new FavorizationModelListener(favoredButton, trait.getFavorization()
        .getStatusModel(), favorizationImageProvider);
    trait.getFavorization().addFavoredChangeListener(listener);
    trait.getFavorization().addFavorableChangeListener(listener);
    favoredButton.addListener(SWT.MouseUp, new FavorizationButtonChangeListener(favoredButton, trait));
  }

  private Label createLabel(Color background, GridData data) {
    Label label = new Label(parent, SWT.NULL);
    label.setLayoutData(data);
    label.setBackground(background);
    return label;
  }
}