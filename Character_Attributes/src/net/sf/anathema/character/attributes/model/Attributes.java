package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.lib.control.change.IChangeListener;

import org.eclipse.osgi.util.NLS;

public class Attributes extends AbstractModel implements IAttributes {

  private final IBasicTrait[] traits;
  private final IChangeListener dirtyListener = new IChangeListener() {
    @Override
    public void changeOccured() {
      setDirty(true);
    }
  };

  public Attributes(IBasicTrait... traits) {
    this.traits = traits;
    for (IBasicTrait basicTrait : traits) {
      basicTrait.getCreationModel().addValueChangeListener(dirtyListener);
      basicTrait.getExperiencedModel().addValueChangeListener(dirtyListener);
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
    throw new IllegalArgumentException(NLS.bind(Messages.Attributes_NotFound_Message, id));
  }
}