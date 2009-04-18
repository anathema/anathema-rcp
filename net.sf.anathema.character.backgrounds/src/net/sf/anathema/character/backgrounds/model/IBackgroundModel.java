package net.sf.anathema.character.backgrounds.model;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public interface IBackgroundModel extends ITraitCollectionModel {

  public String MODEL_ID = "net.sf.anathema.character.backgrounds.model"; //$NON-NLS-1$

  public IBasicTrait addBackground(String name);

  public void addModificationListener(IBackgroundAdditionListener<IBasicTrait> listener);

  public void removeModificationListener(IBackgroundAdditionListener<IBasicTrait> listener);
}