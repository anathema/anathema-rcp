package net.sf.anathema.character.backgrounds;

import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public interface IBackgroundModel extends ITraitCollectionModel {

  public String MODEL_ID = "net.sf.anathema.character.backgrounds.model"; //$NON-NLS-1$

  public void addBackground(String name);

  public void addModificationListener(IBackgroundModificationListener listener);

  public void removeModificationListener(IBackgroundModificationListener listener);
}