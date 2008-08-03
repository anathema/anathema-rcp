package net.sf.anathema.character.trait.collection;

import java.util.List;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.interactive.IIntValueModel;
import net.sf.anathema.character.trait.status.DefaultStatus;
import net.sf.anathema.character.trait.status.ITraitStatus;
import net.sf.anathema.character.trait.status.ITraitStatusModel;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.ui.IUpdatable;
import net.sf.anathema.lib.util.IIdentificate;

import org.eclipse.osgi.util.NLS;

public class TraitCollection extends AbstractModel implements ITraitCollectionModel {
  private final MultiEntryMap<String, IBasicTrait> subTraits = new MultiEntryMap<String, IBasicTrait>();
  private final IBasicTrait[] traits;
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

  public TraitCollection(IBasicTrait... traits) {
    this.traits = traits;
    for (IBasicTrait basicTrait : traits) {
      basicTrait.getCreationModel().addChangeListener(changeListener);
      basicTrait.getExperiencedModel().addChangeListener(changeListener);
      basicTrait.getStatusManager().addChangeListener(changeListener);
    }
  }

  @Override
  public IBasicTrait[] getTraits() {
    return traits;
  }

  @Override
  public IBasicTrait getTrait(String id) {
    for (IBasicTrait trait : traits) {
      if (id.equals(trait.getTraitType().getId())) {
        return trait;
      }
    }
    throw new IllegalArgumentException(NLS.bind(Messages.Trait_NotFound_Message, id));
  }

  @Override
  public boolean contains(String traitId) {
    for (IBasicTrait trait : traits) {
      if (traitId.equals(trait.getTraitType().getId())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    changeControl.addChangeListener(listener);
  }

  private void addPriorityChangeListener(IChangeListener listener) {
    changeControl.addChangeListener(listener);
  }


  @Override
  public void removeChangeListener(IChangeListener listener) {
    changeControl.removeChangeListener(listener);
  }

  @Override
  public void setStatusFor(ITraitStatus newStatus, List< ? extends IIdentificate> statusTraits) {
    for (IBasicTrait trait : getTraits()) {
      ITraitStatusModel statusManager = trait.getStatusManager();
      ITraitStatus status = statusManager.getStatus();
      if (newStatus.equals(status) && !statusTraits.contains(trait.getTraitType())) {
        statusManager.setStatus(new DefaultStatus());
      }
      else if (statusTraits.contains(trait.getTraitType())) {
        statusManager.setStatus(newStatus);
      }
    }
  }

  @Override
  public List<IBasicTrait> getSubTraits(String id) {
    return subTraits.get(id);
  }

  @Override
  public void addSubTrait(final String trait, final IBasicTrait subTrait) {
    final IBasicTrait basicTrait = getTrait(trait);
    subTraits.add(trait, subTrait);
    final IIntValueModel subtraitCreationModel = subTrait.getCreationModel();
    subtraitCreationModel.addChangeListener(changeListener);
    subtraitCreationModel.addChangeListener(new SubTraitAdaptionListener(
        this,
        basicTrait,
        new CreationModelTransformer()));
    final IIntValueModel subtraitExperiencedModel = subTrait.getExperiencedModel();
    subtraitExperiencedModel.addChangeListener(changeListener);
    subtraitExperiencedModel.addChangeListener(new SubTraitAdaptionListener(
        this,
        basicTrait,
        new ExperienceModelTransformer()));
    StatusUpdater statusUpdater = new StatusUpdater(subTrait, basicTrait);
    basicTrait.getStatusManager().addPriorityChangeListener(statusUpdater);
    statusUpdater.stateChanged();
    setDirty(true);
  }

  @Override
  public void setDependencyUpdatable(IUpdatable updatable) {
    dependencyUpdatable = updatable;
  }

  @Override
  public void updateToDependencies() {
    dependencyUpdatable.update();
  }
}