package net.sf.anathema.character.trait;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.character.trait.interactive.IIntValueModel;
import net.sf.anathema.lib.util.IIdentificate;

public interface IBasicTrait {

  public IIntValueModel getCreationModel();

  public IIntValueModel getExperiencedModel();

  public BooleanModel getFavoredModel();

  public IIdentificate getTraitType();
}