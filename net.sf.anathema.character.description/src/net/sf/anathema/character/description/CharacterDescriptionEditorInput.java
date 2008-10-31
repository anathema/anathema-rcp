package net.sf.anathema.character.description;

import java.net.URL;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.model.AbstractCharacterModelEditorInput;
import net.sf.anathema.character.core.repository.ModelDisplayNameProvider;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

public class CharacterDescriptionEditorInput extends AbstractCharacterModelEditorInput<ICharacterDescription> {

  private ICharacterDescription item;

  public CharacterDescriptionEditorInput(IFile file, URL imageUrl, ICharacterDescription description) {
    this(file, imageUrl, description, new DescriptionCharacterDisplayNameProvider(file, description));
  }

  public CharacterDescriptionEditorInput(
      IFile file,
      URL imageUrl,
      ICharacterDescription description,
      IDisplayNameProvider provider) {
    super(
        file,
        imageUrl,
        new ModelDisplayNameProvider(Messages.CharacterDescriptionEditorInput_Description, provider),
        new CharacterDescriptionPersister());
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
  protected String getModelId() {
    return ICharacterDescription.MODEL_ID;
  }

  @Override
  public IFolder getCharacterFolder() {
    return super.getCharacterFolder();
  }
}