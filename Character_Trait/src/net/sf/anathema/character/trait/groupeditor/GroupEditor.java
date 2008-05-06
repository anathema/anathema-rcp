package net.sf.anathema.character.trait.groupeditor;

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
import net.sf.anathema.character.trait.groupeditor.subtrait.SubtraitContainer;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.ControlContribution;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

public class GroupEditor extends AbstractPersistableItemEditorPart<IItem> {

  private final ClassedProvider<ITraitGroupEditorDecoration> decorations = new ClassedProvider<ITraitGroupEditorDecoration>();
  private Composite sectionContent;
  private Composite layoutContainer;

  @Override
  protected IEditorControl createItemEditorControl() {
    return new AbstractItemEditorControl(this) {

      @Override
      public void setFocus() {
        // nothing to do
      }

      @Override
      public void createPartControl(Composite parent) {
        ITraitGroupEditorInput editorInput = (ITraitGroupEditorInput) getEditorInput();
        final FormToolkit toolkit = new FormToolkit(parent.getDisplay());
        SectionFactory sectionFactory = new SectionFactory(toolkit);
        Form form = toolkit.createForm(parent);
        toolkit.decorateFormHeading(form);
        form.setText(getEditorInput().getName());
        form.getBody().setLayout(new FillLayout());
        layoutContainer = toolkit.createComposite(form.getBody());
        decorations.addAll(new TraitGroupEditorDecorationFactory().create());
        ICharacterId characterId = editorInput.getCharacterId();
        List<IDisplayTraitGroup<IInteractiveTrait>> displayGroups = editorInput.createDisplayGroups();
        ColumnLayout columnLayout = new ColumnLayout();
        columnLayout.maxNumColumns = displayGroups.size() + 1;
        layoutContainer.setLayout(columnLayout);
        for (IDisplayTraitGroup<IInteractiveTrait> group : displayGroups) {
          String title = editorInput.getConfiguration().getGroupLabel(group);
          Composite sectionContent = sectionFactory.create(layoutContainer, title);
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
        Section section = toolkit.createSection(layoutContainer, ExpandableComposite.TITLE_BAR);
        section.setText("Crafts");
        Composite composite = createCraftComposite(toolkit, section);

        section.setDescriptionControl(composite);
        sectionContent = toolkit.createComposite(section);
        section.setClient(sectionContent);
        TraitViewFactory factory = new TraitViewFactory(sectionContent, editorInput.getImageProvider(), characterId);
        sectionContent.setLayout(new GridLayout(3, false));
        SubtraitContainer subtraitContainer = new SubtraitContainer(toolkit, factory, editorInput, GroupEditor.this);
        form.getToolBarManager().add(new ControlContribution("craft.composite.contribution.text") {
          @Override
          protected Control createControl(Composite parent) {
            return toolkit.createText(parent, "");
          }
        });
        form.getToolBarManager().add(new ControlContribution("craft.composite.contribution.button") {
          @Override
          protected Control createControl(Composite parent) {
            return toolkit.createButton(parent, "Add Craft", SWT.PUSH);
          }
        });
        form.getToolBarManager().update(true);
        for (final IInteractiveTrait trait : editorInput.createCrafts()) {
          subtraitContainer.addSubTrait(trait);
        }
        IFolder characterFolder = editorInput.getCharacterFolder();
        Display display = layoutContainer.getDisplay();
        final IResourceChangeListener resourceListener = new CharacterPartNameListener(
            GroupEditor.this,
            characterFolder,
            display);
        ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceListener);
        addDisposable(new ResourceChangeListenerDisposable(resourceListener));
        getSite().getPage().addPartListener(new DecorationUpdateListener((GroupEditor) getEditor()));
      }

      private Composite createCraftComposite(final FormToolkit toolkit, Composite parent) {
        Composite composite = toolkit.createComposite(parent);
        composite.setLayout(new GridLayout(2, false));
        toolkit.createText(composite, "");
        toolkit.createButton(composite, "Add Craft", SWT.PUSH);
        return composite;
      }
    };
  }

  public void updateDecorations() {
    for (ITraitGroupEditorDecoration decoration : decorations) {
      decoration.update();
    }
  }

  public ClassedProvider<ITraitGroupEditorDecoration> getDecorations() {
    return decorations;
  }

  public void redraw() {
    sectionContent.getParent().pack(true);
    layoutContainer.layout();
  }
}