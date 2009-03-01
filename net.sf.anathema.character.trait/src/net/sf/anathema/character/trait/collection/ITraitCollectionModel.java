package net.sf.anathema.character.trait.collection;

import java.util.List;

import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.status.ITraitStatus;
import net.sf.anathema.lib.ui.IUpdatable;
import net.sf.anathema.lib.util.IIdentificate;

public interface ITraitCollectionModel extends IModel {

  public IBasicTrait[] getAllTraits();

  public IBasicTrait getTrait(String id);

  public IBasicTrait[] getTraits(String... id);

  public void addSubTrait(String trait, IBasicTrait subTrait);

  public List<IBasicTrait> getSubTraits(String id);

  public void setStatusFor(ITraitStatus newStatus, List< ? extends IIdentificate> traits);

  public void setDependencyUpdatable(IUpdatable updatable);

  public boolean contains(String traitId);
}