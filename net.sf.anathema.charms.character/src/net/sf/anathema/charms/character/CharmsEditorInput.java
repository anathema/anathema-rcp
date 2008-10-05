package net.sf.anathema.charms.character;

import java.net.URL;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.model.AbstractCharacterModelEditorInput;

import org.eclipse.core.resources.IFile;

public class CharmsEditorInput extends AbstractCharacterModelEditorInput<ICharmModel> {

  private final ICharmModel charms;

  public CharmsEditorInput(
      IFile file,
      URL imageUrl,
      ICharmModel charms,
      IDisplayNameProvider displayNameProvider) {
    super(file, imageUrl, displayNameProvider, new CharmsPersister());
    this.charms = charms;
  }

  @Override
  protected String getModelId() {
    return ICharmModel.MODEL_ID;
  }

  @Override
  public ICharmModel getItem() {
    return charms;
  }
}