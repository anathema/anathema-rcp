package net.sf.anathema.character.trait.fake;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.AbstractTraitCollection;
import net.sf.anathema.character.trait.status.ITraitStatus;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.ui.IUpdatable;
import net.sf.anathema.lib.util.IIdentificate;

public class DummyTraitCollection extends AbstractTraitCollection {

  private final List<IBasicTrait> traits = new ArrayList<IBasicTrait>();
  private final MultiEntryMap<String, IBasicTrait> subTraits = new MultiEntryMap<String, IBasicTrait>();

  @Override
  public IBasicTrait[] getAllTraits() {
    return traits.toArray(new IBasicTrait[traits.size()]);
  }

  public void addTrait(final IBasicTrait... addedTraits) {
    for (final IBasicTrait trait : addedTraits) {
      traits.add(trait);
    }
  }

  @Override
  public void addChangeListener(final IChangeListener modelChangeListener) {
    // nothing to do
  }

  @Override
  public void removeChangeListener(final IChangeListener modelChangeListener) {
    // nothing to do
  }

  @Override
  public void setStatusFor(final ITraitStatus newStatus, final List< ? extends IIdentificate> traits) {
    // nothing to do
  }

  @Override
  public void setDependencyUpdatable(final IUpdatable updatable) {
    // nothing to do
  }

  @Override
  public void addSubTrait(final String traitId, final IBasicTrait subTrait) {
    subTraits.add(traitId, subTrait);
  }

  @Override
  public List<IBasicTrait> getSubTraits(final String traitId) {
    return subTraits.get(traitId);
  }
}