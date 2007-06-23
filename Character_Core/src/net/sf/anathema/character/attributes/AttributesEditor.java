package net.sf.anathema.character.attributes;

import net.sf.anathema.basics.item.editor.AbstractPersistableItemEditorPart;
import net.sf.anathema.basics.item.editor.IPersistableItemEditor;
import net.sf.anathema.character.core.traitview.CanvasIntValueDisplay;
import net.sf.anathema.character.trait.IDisplayTrait;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class AttributesEditor extends AbstractPersistableItemEditorPart<IAttributes> implements IPersistableItemEditor {

  public static final String EDITOR_ID = "net.sf.anathema.character.attributes.editor"; //$NON-NLS-1$

  //TODO Dispose images when done.
  @Override
  public void createPartControl(Composite parent) {
    Image passiveImage = createImage("BorderUnselectedButton16.png"); //$NON-NLS-1$
    Image activeImage = createImage("BorderSolarButton16.png"); //$NON-NLS-1$
    AttributesEditorInput editorInput = (AttributesEditorInput) getEditorInput();
    parent.setLayout(new GridLayout(2, false));
    for (final IDisplayTrait trait : editorInput.createDisplayTraits()) {
      new Label(parent, SWT.NULL).setText(AttributeMessages.get(trait.getTraitType().getId()));
      final CanvasIntValueDisplay display = new CanvasIntValueDisplay(
          parent,
          passiveImage,
          activeImage,
          trait.getMaximalValue());
      trait.addValueChangeListener(new IChangeListener() {
        @Override
        public void changeOccured() {
          display.setValue(trait.getValue());
        }
      });
      display.addIntValueChangedListener(new IIntValueChangedListener() {
        @Override
        public void valueChanged(int newValue) {
          trait.setValue(newValue);
        }
      });
      display.setValue(trait.getValue());
    }
  }

  @Override
  public void setFocus() {
    // nothing to do
  }

  private Image createImage(String imageName) {
    return ImageDescriptor.createFromFile(CanvasIntValueDisplay.class, imageName).createImage();
  }

}