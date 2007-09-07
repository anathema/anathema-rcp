package net.sf.anathema.character.trait.collection;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.lib.control.change.ChangeControl;

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
      basicTrait.getCreationModel().addChangeListener(changeListener);
      basicTrait.getExperiencedModel().addChangeListener(changeListener);
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
}