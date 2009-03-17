package net.sf.anathema.character.trait;

import net.sf.anathema.character.trait.interactive.IIntValueModel;
import net.sf.anathema.character.trait.status.ITraitStatusModel;
import net.sf.anathema.lib.util.IIdentificate;

public interface IBasicTrait {

  public static final int ESSENCE_MAX = 10;

  public IIntValueModel getCreationModel();

  public IIntValueModel getExperiencedModel();
  
  public ITraitStatusModel getStatusManager();

  public IIdentificate getTraitType();
  
  public boolean isExperiencedValueSet();
}