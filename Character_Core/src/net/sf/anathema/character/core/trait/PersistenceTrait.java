package net.sf.anathema.character.core.trait;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.lib.control.AggregatedChangeManagement;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public class PersistenceTrait extends AggregatedChangeManagement implements IPersistenceTrait, ITrait {

  private final ITrait creationTrait;
  private final ITrait experiencedTrait;
  private boolean isExperienced = false;

  public PersistenceTrait(ITrait creationTrait, ITrait experiencedTrait) {
    Ensure.ensureArgumentEquals("Must have save trait type.", creationTrait.getTraitType(), experiencedTrait.getTraitType()); //$NON-NLS-1$
    this.creationTrait = creationTrait;
    this.experiencedTrait = experiencedTrait;
    setChangeManagments(creationTrait, experiencedTrait);
  }

  @Override
  public int getCreationValue() {
    return creationTrait.getValue();
  }

  @Override
  public int getExperiencedValue() {
    return experiencedTrait.getValue();
  }

  @Override
  public IIdentificate getTraitType() {
    return creationTrait.getTraitType();
  }

  @Override
  public void addValueChangeListener(IChangeListener listener) {
    creationTrait.addValueChangeListener(listener);
    experiencedTrait.addValueChangeListener(listener);
  }

  @Override
  public int getMaximalValue() {
    return getCurrentTrait().getMaximalValue();
  }

  @Override
  public int getValue() {
    return getCurrentTrait().getValue();
  }

  @Override
  public void setValue(int value) {
    getCurrentTrait().setValue(value);
  }
  
  private ITrait getCurrentTrait() {
    return isExperienced ? experiencedTrait : creationTrait;
  }
}