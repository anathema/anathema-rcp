package net.sf.anathema.character.attributes.model;

import net.sf.anathema.basics.eclipse.resource.ResourceChangeListenerDisposable;
import net.sf.anathema.basics.item.editor.AbstractPersistableItemEditorPart;
import net.sf.anathema.basics.swt.layout.GridDataFactory;
import net.sf.anathema.character.attributes.AttributesPlugin;
import net.sf.anathema.character.core.listening.CharacterPartNameListener;
import net.sf.anathema.character.core.traitview.CanvasIntValueDisplay;
import net.sf.anathema.character.trait.IDisplayTrait;
import net.sf.anathema.character.trait.TraitPresenter;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.lib.ui.IIntValueView;

import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class AttributesEditor extends AbstractPersistableItemEditorPart<IAttributes> {

  public static final String EDITOR_ID = "net.sf.anathema.character.attributes.editor"; //$NON-NLS-1$

  @Override
  public void createPartControl(Composite parent) {
    Image passiveImage = createImage(AttributesPlugin.UNSELECTED_BUTTON);
    Image activeImage = createImage(AttributesPlugin.SELECTED_BUTTON);
    AttributesEditorInput editorInput = (AttributesEditorInput) getEditorInput();
    parent.setLayout(new GridLayout(2, false));
    for (IDisplayTraitGroup group : editorInput.createDisplayGroups()) {
      createLabel(parent, GridDataFactory.createHorizontalSpanData(2)).setText(AttributeMessages.get(group.getId()));
      for (final IDisplayTrait trait : group.getTraits()) {
        String text = AttributeMessages.get(trait.getTraitType().getId());
        createLabel(parent, GridDataFactory.createIndentData(5)).setText(text);
        final IIntValueView view = new CanvasIntValueDisplay(parent, passiveImage, activeImage, trait.getMaximalValue());
        new TraitPresenter().initPresentation(trait, view);
        addDisposable(trait);
      }
    }
    final IResourceChangeListener resourceListener = new CharacterPartNameListener(
        this,
        editorInput.getCharacterFolder(),
        parent.getDisplay());
    ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceListener);
    addDisposable(new ResourceChangeListenerDisposable(resourceListener));
  }

  private Label createLabel(Composite parent, GridData data) {
    Label label = new Label(parent, SWT.NULL);
    label.setLayoutData(data);
    return label;
  }

  @Override
  public void setFocus() {
    // nothing to do
  }

  private Image createImage(String imageName) {
    return AttributesPlugin.getDefaultInstance().getImageRegistry().get(imageName);
  }
}