package net.sf.anathema.charms.character;

import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.charms.character.model.VirtualCharmEvaluation;
import net.sf.anathema.charms.character.preference.CharmPreferenceFactory;
import net.sf.anathema.charms.character.preference.ICharmPreferences;
import net.sf.anathema.charms.view.ZestView;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class CharmsEditorControl extends AbstractItemEditorControl {

  private final IExperience experience;

  public CharmsEditorControl(CharmsEditor editor, IExperience experience) {
    super(editor);
    this.experience = experience;
  }

  @Override
  protected void createPartControl(FormToolkit toolkit, Composite body) {
    CharmsEditorInput editorInput = getEditorInput();
    ICharmPreferences preferences = CharmPreferenceFactory.create();
    CharacterCharmVisuals charmVisuals = new CharacterCharmVisuals(
        editorInput.getItem(),
        experience,
        preferences,
        new VirtualCharmEvaluation());
    ZestView zestView = new ZestView(charmVisuals, editorInput.getTreeId(), experience);
    zestView.createPartControl(body);
    addDisposable(zestView);
  }

  public CharmsEditorInput getEditorInput() {
    return (CharmsEditorInput) getEditor().getPersistableEditorInput();
  }

  @Override
  public void setFocus() {
    // nothing to do
  }
}