package net.sf.anathema.character.trait;

import net.sf.anathema.lib.util.IIdentificate;

public interface IBasicTrait {

  public IIntValueModel getCreationModel();

  public IIntValueModel getExperiencedModel();

  public IIdentificate getTraitType();
}