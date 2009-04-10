package net.sf.anathema.character.backgrounds;

import java.net.URL;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.core.model.AbstractCharacterModelEditorInput;

import org.eclipse.core.resources.IFile;

public class BackgroundEditorInput extends AbstractCharacterModelEditorInput<IBackgroundModel> {

  private final IBackgroundModel model;
  private final ICharacterType characterType;

  public BackgroundEditorInput(
      IFile file,
      URL imageUrl,
      IDisplayNameProvider displayNameProvider,
      ICharacterType characterType,
      IBackgroundModel model) {
    super(file, imageUrl, displayNameProvider, new BackgroundPersister());
    this.characterType = characterType;
    this.model = model;
  }

  @Override
  protected String getModelId() {
    return IBackgroundModel.MODEL_ID;
  }

  @Override
  public IBackgroundModel getItem() {
    return model;
  }

  public ICharacterType getCharacterType() {
    return characterType;
  }
}