package net.sf.anathema.character.description.editor;

import java.net.URL;

import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.editors.AbstractModelPersistableFactory;
import net.sf.anathema.character.core.editors.CharacterModelPersistable;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.description.CharacterDescriptionEditorInput;
import net.sf.anathema.character.description.ICharacterDescription;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IMemento;

public class CharacterDescriptionPersistableFactory extends AbstractModelPersistableFactory {

  public CharacterDescriptionPersistableFactory() {
    this(ModelCache.getInstance());
  }

  public CharacterDescriptionPersistableFactory(IModelCollection modelCollection) {
    super(modelCollection);
  }

  @Override
  public IAdaptable createElement(IMemento memento) {
    try {
      URL imageUrl = new URL(memento.getString(CharacterModelPersistable.PROP_IMAGE_DESCRIPTOR_URL));
      ICharacterDescription description = (ICharacterDescription) getModel(memento, ICharacterDescription.MODEL_ID);
      return new CharacterDescriptionEditorInput(getModelFile(memento), imageUrl, description);
    }
    catch (Exception e) {
      // TODO Errorhandling (UrlException separat?)
      return null;
    }
  }
}