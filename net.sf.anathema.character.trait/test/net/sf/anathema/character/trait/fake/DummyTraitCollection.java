package net.sf.anathema.character.trait.fake;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.status.ITraitStatus;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.ui.IUpdatable;
import net.sf.anathema.lib.util.IIdentificate;

public class DummyTraitCollection extends AbstractModel implements ITraitCollectionModel {

  private final List<IBasicTrait> traits = new ArrayList<IBasicTrait>();
  private final MultiEntryMap<String, IBasicTrait> subTraits = new MultiEntryMap<String, IBasicTrait>();

  @Override
  public IBasicTrait[] getTraits() {
    return traits.toArray(new IBasicTrait[traits.size()]);
  }

  public void addTrait(IBasicTrait... addedTraits) {
    for (IBasicTrait trait : addedTraits) {
      traits.add(trait);
    }
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

  @Override
  public void setStatusFor(ITraitStatus newStatus, List< ? extends IIdentificate> traits) {
    // nothing to do
  }

  @Override
  public void setDependencyUpdatable(IUpdatable updatable) {
    // nothing to do
  }

  @Override
  public boolean contains(String traitId) {
    return getTrait(traitId) != null;
  }

  @Override
  public void addSubTrait(String traitId, IBasicTrait subTrait) {
    subTraits.add(traitId, subTrait);
  }

  @Override
  public List<IBasicTrait> getSubTraits(String traitId) {
    return subTraits.get(traitId);
  }
}