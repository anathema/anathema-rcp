package net.sf.anathema.character.description;

import java.net.URL;

import net.sf.anathema.character.core.model.AbstractCharacterModelEditorInput;

import org.eclipse.core.resources.IFile;
import org.eclipse.osgi.util.NLS;

public class CharacterDescriptionEditorInput extends AbstractCharacterModelEditorInput<ICharacterDescription> {

  private ICharacterDescription item;

  public CharacterDescriptionEditorInput(IFile file, URL imageUrl, ICharacterDescription description) {
    super(file, imageUrl, null, new CharacterDescriptionPersister());
    this.item = description;
  }

  @Override
  public ICharacterDescription getItem() {
    return item;
  }

  public void setItem(ICharacterDescription item) {
    this.item = item;
  }

  @Override
  public String getToolTipText() {
    return getName();
  }

  @Override
  public String getName() {
    return NLS.bind(Messages.CharacterDescriptionEditorInput_Description_Message, item.getName().getText());
  }

  @Override
  protected String getModelId() {
    return ICharacterDescription.MODEL_ID;
  }
}