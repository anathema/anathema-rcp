package net.sf.anathema.character.attributes.model;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.rules.ITraitTemplate;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.util.Identificate;

import org.eclipse.osgi.util.NLS;

public class Attributes extends AbstractModel implements IAttributes {

  private final IBasicTrait[] traits;
  private final IChangeListener changeListener = new IChangeListener() {
    @Override
    public void stateChanged() {
      changeControl.fireChangedEvent();
      setDirty(true);
    }
  };
  private final ChangeControl changeControl = new ChangeControl();

  public Attributes(IBasicTrait... traits) {
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
    throw new IllegalArgumentException(NLS.bind(Messages.Attributes_NotFound_Message, id));
  }

  public static IAttributes create(ITraitGroup[] groups, ITraitTemplate template) {
    List<IBasicTrait> basicTraits = new ArrayList<IBasicTrait>();
    for (ITraitGroup group : groups) {
      for (String traitId : group.getTraitIds()) {
        BasicTrait basicTrait = new BasicTrait(new Identificate(traitId));
        basicTraits.add(basicTrait);
        basicTrait.getCreationModel().setValue(template.getMinimalValue());
      }
    }
    return new Attributes(basicTraits.toArray(new BasicTrait[basicTraits.size()]));
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