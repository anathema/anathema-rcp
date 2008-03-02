package net.sf.anathema.character.trait.collection;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.status.DefaultStatus;
import net.sf.anathema.character.trait.status.ITraitStatus;
import net.sf.anathema.character.trait.status.ITraitStatusModel;
import net.sf.anathema.character.trait.template.ITraitTemplate;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import org.eclipse.osgi.util.NLS;

public class TraitCollection extends AbstractModel implements ITraitCollectionModel {

  private final IBasicTrait[] traits;
  private final IChangeListener changeListener = new IChangeListener() {
    @Override
    public void stateChanged() {
      changeControl.fireChangedEvent();
      setDirty(true);
    }
  };
  private final ChangeControl changeControl = new ChangeControl();

  public TraitCollection(IBasicTrait... traits) {
    this.traits = traits;
    for (IBasicTrait basicTrait : traits) {
      // TODO wieder abmelden
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
  public void addChangeListener(IChangeListener listener) {
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

  public static ITraitCollectionModel create(ITraitGroup[] groups, ITraitTemplate template) {
    List<IBasicTrait> basicTraits = new ArrayList<IBasicTrait>();
    for (ITraitGroup group : groups) {
      for (String traitId : group.getTraitIds()) {
        BasicTrait basicTrait = new BasicTrait(new Identificate(traitId));
        basicTraits.add(basicTrait);
        basicTrait.getCreationModel().setValue(template.getMinimalValue());
      }
    }
    return new TraitCollection(basicTraits.toArray(new BasicTrait[basicTraits.size()]));
  }
}