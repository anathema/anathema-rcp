package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.basics.eclipse.resource.ResourceChangeListenerDisposable;
import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.basics.item.editor.AbstractPersistableItemEditorPart;
import net.sf.anathema.basics.item.editor.IEditorControl;
import net.sf.anathema.basics.swt.layout.GridDataFactory;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.listening.CharacterPartNameListener;
import net.sf.anathema.character.core.traitview.IExtendableIntValueView;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class TraitGroupEditor extends AbstractPersistableItemEditorPart<IItem> {

  private final ClassedProvider<ITraitGroupEditorDecoration> decorations = new ClassedProvider<ITraitGroupEditorDecoration>();

  @Override
  protected IEditorControl createItemEditorControl() {
    return new AbstractItemEditorControl(this) {

      @Override
      public void setFocus() {
        // nothing to do
      }

      @Override
      public void createPartControl(Composite parent) {
        parent.setLayout(new FillLayout());
        final ScrolledComposite scroll = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        final Composite container = new Composite(scroll, SWT.NONE);
        scroll.setContent(container);
        decorations.addAll(new TraitGroupEditorDecorationFactory().create());
        ITraitGroupEditorInput editorInput = (ITraitGroupEditorInput) getEditorInput();
        ICharacterId characterId = editorInput.getCharacterId();
        TraitViewFactory factory = new TraitViewFactory(container, editorInput.getImageProvider(), characterId);
        container.setLayout(new GridLayout(3, false));
        for (IDisplayTraitGroup<IInteractiveTrait> group : editorInput.createDisplayGroups()) {
          createLabel(container, GridDataFactory.createHorizontalSpanData(3)).setText(editorInput.getGroupLabel(group));
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
        Display display = container.getDisplay();
        final IResourceChangeListener resourceListener = new CharacterPartNameListener(
            TraitGroupEditor.this,
            characterFolder,
            display);
        ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceListener);
        addDisposable(new ResourceChangeListenerDisposable(resourceListener));
        container.setSize(container.computeSize(SWT.DEFAULT, SWT.DEFAULT));
      }

      private Label createLabel(Composite parent, GridData data) {
        Label label = new Label(parent, SWT.NULL);
        label.setLayoutData(data);
        return label;
      }
    };
  }

  public <T extends ITraitGroupEditorDecoration> T getDecoration(Class<T> clazz) {
    return decorations.get(clazz);
  }
}