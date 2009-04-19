package net.sf.anathema.character.trait.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.disy.commons.core.model.listener.IChangeListener;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.internal.CreationModelTransformer;
import net.sf.anathema.character.trait.collection.internal.ExperiencedModelTransformer;
import net.sf.anathema.character.trait.collection.internal.StatusUpdater;
import net.sf.anathema.character.trait.collection.internal.SubTraitAdaptionListener;
import net.sf.anathema.character.trait.interactive.IIntValueModel;
import net.sf.anathema.character.trait.status.DefaultStatus;
import net.sf.anathema.character.trait.status.ITraitStatus;
import net.sf.anathema.character.trait.status.ITraitStatusModel;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.ui.IUpdatable;
import net.sf.anathema.lib.util.IIdentificate;

public class TraitCollection extends AbstractTraitCollection {
  private final MultiEntryMap<String, IBasicTrait> subTraits = new MultiEntryMap<String, IBasicTrait>();
  private final List<IBasicTrait> traits = new ArrayList<IBasicTrait>();
  private final IChangeListener changeListener = new IChangeListener() {
    @Override
    public void stateChanged() {
      changeControl.fireChangedEvent();
      setDirty(true);
    }
  };
  private final ChangeControl changeControl = new ChangeControl();
  private IUpdatable dependencyUpdatable = new IUpdatable() {
    @Override
    public void update() {
      // nothing to do
    }
  };

  public TraitCollection(final IBasicTrait... traits) {
    for (final IBasicTrait basicTrait : traits) {
      addTrait(basicTrait);
    }
  }

  protected void addTrait(IBasicTrait trait) {
    this.traits.add(trait);
    trait.getCreationModel().addChangeListener(changeListener);
    trait.getExperiencedModel().addChangeListener(changeListener);
    trait.getStatusManager().addChangeListener(changeListener);
  }

  @Override
  public Iterable<IBasicTrait> getAllTraits() {
    return traits;
  }

  @Override
  public Iterator<IBasicTrait> iterator() {
    return traits.iterator();
  }

  @Override
  public void addChangeListener(final IChangeListener listener) {
    changeControl.addChangeListener(listener);
  }

  @Override
  public void removeChangeListener(final IChangeListener listener) {
    changeControl.removeChangeListener(listener);
  }

  @Override
  public void setStatusFor(final ITraitStatus newStatus, final List< ? extends IIdentificate> statusTraits) {
    for (final IBasicTrait trait : getAllTraits()) {
      final ITraitStatusModel statusManager = trait.getStatusManager();
      final ITraitStatus status = statusManager.getStatus();
      if (newStatus.equals(status) && !statusTraits.contains(trait.getTraitType())) {
        statusManager.setStatus(new DefaultStatus());
      }
      else if (statusTraits.contains(trait.getTraitType())) {
        statusManager.setStatus(newStatus);
      }
    }
  }

  @Override
  public List<IBasicTrait> getSubTraits(final String id) {
    return subTraits.get(id);
  }

  @Override
  public void addSubTrait(final String trait, final IBasicTrait subTrait) {
    final IBasicTrait parentTrait = getTrait(trait);
    subTraits.add(trait, subTrait);
    addSubtraitListeners(parentTrait, subTrait, new CreationModelTransformer());
    addSubtraitListeners(parentTrait, subTrait, new ExperiencedModelTransformer());
    addStatusUpdateForSubtraits(subTrait, parentTrait);
    setDirty(true);
  }

  private void addSubtraitListeners(
      final IBasicTrait parentTrait,
      final IBasicTrait subTrait,
      final ITransformer<IBasicTrait, IIntValueModel> transformer) {
    final IIntValueModel valueModel = transformer.transform(subTrait);
    valueModel.addChangeListener(changeListener);
    valueModel.addChangeListener(new SubTraitAdaptionListener(this, parentTrait, transformer));
  }

  private void addStatusUpdateForSubtraits(final IBasicTrait subTrait, final IBasicTrait parentTrait) {
    final StatusUpdater statusUpdater = new StatusUpdater(subTrait, parentTrait);
    parentTrait.getStatusManager().addPriorityChangeListener(statusUpdater);
    statusUpdater.stateChanged();
  }

  @Override
  public void setDependencyUpdatable(final IUpdatable updatable) {
    dependencyUpdatable = updatable;
  }

  @Override
  public void updateToDependencies() {
    dependencyUpdatable.update();
  }
}