package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.basics.eclipse.resource.ResourceChangeListenerDisposable;
import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.editor.AbstractPersistableItemEditorPart;
import net.sf.anathema.basics.swt.layout.GridDataFactory;
import net.sf.anathema.character.core.listening.CharacterPartNameListener;
import net.sf.anathema.character.trait.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class TraitGroupEditor extends AbstractPersistableItemEditorPart<IItem> {

  @Override
  public void createPartControl(Composite parent) {
    ITraitGroupEditorInput editorInput = (ITraitGroupEditorInput) getEditorInput();
    TraitViewFactory factory = new TraitViewFactory(parent, editorInput);
    parent.setLayout(new GridLayout(3, false));
    for (IDisplayTraitGroup group : editorInput.createDisplayGroups()) {
      createLabel(parent, GridDataFactory.createHorizontalSpanData(3)).setText(editorInput.getGroupLabel(group));
      for (final IDisplayTrait trait : group.getTraits()) {
        String label = editorInput.getTraitLabel(trait.getTraitType());
        factory.create(label, trait);
        addDisposable(trait);
      }
    }
    IFolder characterFolder = editorInput.getCharacterFolder();
    Display display = parent.getDisplay();
    final IResourceChangeListener resourceListener = new CharacterPartNameListener(this, characterFolder, display);
    ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceListener);
    addDisposable(new ResourceChangeListenerDisposable(resourceListener));
  }

  private Label createLabel(Composite parent, GridData data) {
    Label label = new Label(parent, SWT.NULL);
    label.setLayoutData(data);
    return label;
  }

//  public void markBonusPoints() {
//    ITraitGroupEditorInput editorInput = (ITraitGroupEditorInput) getEditorInput();
//    // TODO: Punkte berechnen und auf jedem Trait markieren.
//  }

  @Override
  public void setFocus() {
    // nothing to do
  }

}