package net.sf.anathema.character.trait.collection;

import java.util.List;

import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.status.ITraitStatus;
import net.sf.anathema.lib.util.IIdentificate;

public interface ITraitCollectionModel extends IModel {
  
  public IBasicTrait[] getTraits();

  public IBasicTrait getTrait(String id);

  public void setStatusFor(ITraitStatus newStatus, List< ? extends IIdentificate> traits);
}