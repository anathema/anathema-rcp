package net.sf.anathema.charms.character.editor;

import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.character.core.character.Character;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.charms.character.modify.CharmLearner;
import net.sf.anathema.charms.character.preference.CharmPreferenceFactory;
import net.sf.anathema.charms.character.preference.ICharmPreferences;
import net.sf.anathema.charms.character.special.VirtualCharms;
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
    ICharacter character = getCharacter();
    VirtualCharms virtualCharms = new VirtualCharms();
    CharmLearner learningCharacter = new CharmLearner(virtualCharms, preferences, character);
    CharacterCharmVisuals charmVisuals = new CharacterCharmVisuals(learningCharacter);
    ZestView zestView = new ZestView(charmVisuals, editorInput.getTreeId(), experience);
    zestView.createPartControl(body);
    addDisposable(zestView);
  }

  private ICharacter getCharacter() {
    IModelIdentifier modelIdentifier = (IModelIdentifier) getEditorInput().getAdapter(IModelIdentifier.class);
    ICharacterId characterId = modelIdentifier.getCharacterId();
    return Character.From(characterId, ModelCache.getInstance());
  }

  public CharmsEditorInput getEditorInput() {
    return (CharmsEditorInput) getEditor().getPersistableEditorInput();
  }

  @Override
  public void setFocus() {
    // nothing to do
  }
}