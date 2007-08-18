package net.sf.anathema.character.attributes.points;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.attributes.model.IAttributes;
import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.character.trait.IBasicTrait;

public class DummyAttributes extends AbstractModel  implements IAttributes {

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
}