package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.basics.eclipse.resource.ResourceChangeListenerDisposable;
import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.editor.AbstractPersistableItemEditorPart;
import net.sf.anathema.basics.swt.layout.GridDataFactory;
import net.sf.anathema.character.core.listening.CharacterPartNameListener;
import net.sf.anathema.character.core.traitview.IExtendableIntValueView;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;

public class TraitGroupEditor extends AbstractPersistableItemEditorPart<IItem> {

  private final ClassedProvider<ITraitGroupEditorDecoration> decorations = new ClassedProvider<ITraitGroupEditorDecoration>();

  @Override
  public void createPartControlForItem(Composite parent) {
    decorations.addAll(new TraitGroupEditorDecorationFactory().create());
    ITraitGroupEditorInput editorInput = (ITraitGroupEditorInput) getEditorInput();
    TraitViewFactory factory = new TraitViewFactory(parent, editorInput.getImageProvider());
    parent.setLayout(new GridLayout(3, false));
    for (IDisplayTraitGroup<IInteractiveTrait> group : editorInput.createDisplayGroups()) {
      createLabel(parent, GridDataFactory.createHorizontalSpanData(3)).setText(editorInput.getGroupLabel(group));
      for (final IInteractiveTrait trait : group.getTraits()) {
        String label = editorInput.getTraitLabel(trait.getTraitType());
        final IExtendableIntValueView view = factory.create(label, trait);
        for (ITraitGroupEditorDecoration decoration : decorations) {
          decoration.decorate(trait, view, editorInput);
        }
        addDisposable(trait);
      }
    }
    IFolder characterFolder = editorInput.getCharacterFolder();
    Display display = parent.getDisplay();
    final IResourceChangeListener resourceListener = new CharacterPartNameListener(this, characterFolder, display);
    ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceListener);
    addDisposable(new ResourceChangeListenerDisposable(resourceListener));
  }

  @Override
  protected void initForItem(IEditorSite site, IEditorInput input) {
    // nothing to do
  }

  public <T extends ITraitGroupEditorDecoration> T getDecoration(Class<T> clazz) {
    return decorations.get(clazz);
  }

  private Label createLabel(Composite parent, GridData data) {
    Label label = new Label(parent, SWT.NULL);
    label.setLayoutData(data);
    return label;
  }
}