package net.sf.anathema.character.attributes.points;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class DummyTraitCollection extends AbstractModel  implements ITraitCollectionModel {

  private final List<IBasicTrait> traits = new ArrayList<IBasicTrait>();
  
  @Override
  public IBasicTrait[] getTraits() {
    return traits.toArray(new IBasicTrait[traits.size()]);
  }
  
  public void addTrait(IBasicTrait trait) {
    traits.add(trait);
  }

  @Override
  public IBasicTrait getTrait(String id) {
    for (IBasicTrait trait : getTraits()) {
      if (id.equals(trait.getTraitType().getId())) {
        return trait;
      }
    }
    return null;
  }

  @Override
  public void addChangeListener(IChangeListener modelChangeListener) {
    // nothing to do
  }

  @Override
  public void removeChangeListener(IChangeListener modelChangeListener) {
    // nothing to do
  }
}