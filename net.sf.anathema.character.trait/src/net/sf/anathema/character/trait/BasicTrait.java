package net.sf.anathema.character.trait;

import java.text.MessageFormat;

import net.sf.anathema.character.trait.interactive.IIntValueModel;
import net.sf.anathema.character.trait.interactive.IntValueModel;
import net.sf.anathema.character.trait.status.ITraitStatusModel;
import net.sf.anathema.character.trait.status.TraitStatusModel;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class BasicTrait implements IBasicTrait {

  private final IIdentificate traitType;
  private final IntValueModel creationModel = new IntValueModel();
  private final IntValueModel experiencedModel = new IntValueModel();
  private final TraitStatusModel statusManager = new TraitStatusModel();

  public BasicTrait(String traitId) {
    this(new Identificate(traitId));
  }

  public BasicTrait(IIdentificate traitType) {
    this.traitType = traitType;
    creationModel.setValue(0);
    experiencedModel.setValue(-1);
  }

  @Override
  public IIntValueModel getCreationModel() {
    return creationModel;
  }

  @Override
  public IIntValueModel getExperiencedModel() {
    return experiencedModel;
  }

  @Override
  public IIdentificate getTraitType() {
    return traitType;
  }

  @Override
  public ITraitStatusModel getStatusManager() {
    return statusManager;
  }

  public int getListenerCount() {
    return creationModel.getListenerCount() + experiencedModel.getListenerCount();
  }

  @Override
  public boolean isExperiencedValueSet() {
    return getExperiencedModel().getValue() > 0;
  }

  @Override
  public String toString() {
    return MessageFormat.format(
        "{0}[{1},{2},{3}]", //$NON-NLS-1$
        getTraitType().getId(),
        getCreationModel().getValue(),
        getExperiencedModel().getValue(),
        getStatusManager().getStatus());
  }
}