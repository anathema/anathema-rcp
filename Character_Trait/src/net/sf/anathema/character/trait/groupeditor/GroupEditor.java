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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
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
        final Form form = toolkit.createForm(parent);
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
          Composite content = sectionFactory.create(layoutContainer, title);
          content.setLayout(new GridLayout(3, false));
          TraitViewFactory factory = new TraitViewFactory(content, editorInput.getImageProvider(), characterId);
          for (final IInteractiveTrait trait : group.getTraits()) {
            String label = editorInput.getConfiguration().getTraitLabel(trait.getTraitType());
            final IExtendableIntValueView view = factory.create(label, toolkit, trait);
            for (ITraitGroupEditorDecoration decoration : decorations) {
              decoration.decorate(trait, view, editorInput);
            }
            addDisposable(trait);
          }
        }
        if (editorInput.supportsSubTraits()) {
          createCraftSection(editorInput, toolkit, form, characterId);
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

      private void createCraftSection(
          final ITraitGroupEditorInput editorInput,
          final FormToolkit toolkit,
          final Form form,
          ICharacterId characterId) {
        Section section = toolkit.createSection(layoutContainer, ExpandableComposite.TITLE_BAR);
        section.setText("Crafts");
        sectionContent = toolkit.createComposite(section);
        section.setClient(sectionContent);
        TraitViewFactory factory = new TraitViewFactory(sectionContent, editorInput.getImageProvider(), characterId);
        sectionContent.setLayout(new GridLayout(3, false));
        final SubtraitContainer subtraitContainer = new SubtraitContainer(
            toolkit,
            factory,
            editorInput,
            GroupEditor.this);
        final Text[] text = new Text[1];
        form.getToolBarManager().add(new ControlContribution("craft.composite.contribution.text") {
          @Override
          protected Control createControl(Composite parent) {
            final Text craftTextField = toolkit.createText(parent, "");
            text[0] = craftTextField;
            return craftTextField;
          }
        });
        form.getToolBarManager().add(new ControlContribution("craft.composite.contribution.button") {
          @Override
          protected Control createControl(Composite parent) {
            final Button addCraftButton = toolkit.createButton(parent, "Add Craft", SWT.PUSH);
            addCraftButton.addMouseListener(new MouseAdapter() {
              @Override
              public void mouseUp(MouseEvent e) {
                IInteractiveTrait craft = editorInput.addSubTrait("Craft", text[0].getText());
                subtraitContainer.addSubTrait(craft);
              }
            });
            text[0].addModifyListener(new ModifyListener() {
              @Override
              public void modifyText(ModifyEvent e) {
                List<IInteractiveTrait> crafts = editorInput.getSubTraits("Craft");
                for (IInteractiveTrait craft : crafts) {
                  if (craft.getTraitType().getId().equals(text[0].getText())) {
                    addCraftButton.setEnabled(false);
                    return;
                  }
                }
                addCraftButton.setEnabled(true);
              }
            });
            return addCraftButton;
          }
        });
        form.getToolBarManager().update(true);
        for (final IInteractiveTrait trait : editorInput.getSubTraits("Craft")) {
          subtraitContainer.addSubTrait(trait);
        }
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