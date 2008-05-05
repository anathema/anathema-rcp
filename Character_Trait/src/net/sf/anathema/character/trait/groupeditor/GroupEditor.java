package net.sf.anathema.character.trait.groupeditor;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.resource.ResourceChangeListenerDisposable;
import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.basics.item.editor.AbstractPersistableItemEditorPart;
import net.sf.anathema.basics.item.editor.IEditorControl;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.listening.CharacterPartNameListener;
import net.sf.anathema.character.core.traitview.IExtendableIntValueView;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class GroupEditor extends AbstractPersistableItemEditorPart<IItem> {

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
        FormToolkit toolkit = new FormToolkit(parent.getDisplay());
        SectionFactory sectionFactory = new SectionFactory(toolkit);
        Form form = toolkit.createForm(parent);
        toolkit.decorateFormHeading(form);
        form.setText(getEditorInput().getName());
        form.getBody().setLayout(new FillLayout());
        final Composite container = toolkit.createComposite(form.getBody());
        decorations.addAll(new TraitGroupEditorDecorationFactory().create());
        ITraitGroupEditorInput editorInput = (ITraitGroupEditorInput) getEditorInput();
        ICharacterId characterId = editorInput.getCharacterId();
        List<IDisplayTraitGroup<IInteractiveTrait>> displayGroups = editorInput.createDisplayGroups();
        ColumnLayout columnLayout = new ColumnLayout();
        columnLayout.maxNumColumns = displayGroups.size() + 1;
        container.setLayout(columnLayout);
        for (IDisplayTraitGroup<IInteractiveTrait> group : displayGroups) {
          String title = editorInput.getConfiguration().getGroupLabel(group);
          Composite sectionContent = sectionFactory.create(container, title);
          sectionContent.setLayout(new GridLayout(3, false));
          TraitViewFactory factory = new TraitViewFactory(sectionContent, editorInput.getImageProvider(), characterId);
          for (final IInteractiveTrait trait : group.getTraits()) {
            String label = editorInput.getConfiguration().getTraitLabel(trait.getTraitType());
            final IExtendableIntValueView view = factory.create(label, toolkit, trait);
            for (ITraitGroupEditorDecoration decoration : decorations) {
              decoration.decorate(trait, view, editorInput);
            }
            addDisposable(trait);
          }
        }
        Composite sectionContent = sectionFactory.create(container, "Crafts", "Right click to remove");
        TraitViewFactory factory = new TraitViewFactory(sectionContent, editorInput.getImageProvider(), characterId);
        sectionContent.setLayout(new GridLayout(3, false));
        for (final IInteractiveTrait trait : editorInput.createCrafts()) {
          String label = trait.getTraitType().getId();
          final IExtendableIntValueView view = factory.create(label, toolkit, trait);
          for (ITraitGroupEditorDecoration decoration : decorations) {
            decoration.decorate(trait, view, editorInput);
          }
          addDisposable(trait);
        }
        
        IFolder characterFolder = editorInput.getCharacterFolder();
        Display display = container.getDisplay();
        final IResourceChangeListener resourceListener = new CharacterPartNameListener(
            GroupEditor.this,
            characterFolder,
            display);
        ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceListener);
        addDisposable(new ResourceChangeListenerDisposable(resourceListener));
        getSite().getPage().addPartListener(new DecorationUpdateListener((GroupEditor) getEditor()));
      }
    };
  }

  public void updateDecorations() {
    for (ITraitGroupEditorDecoration decoration : decorations) {
      decoration.update();
    }
  }
}