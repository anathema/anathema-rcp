package net.sf.anathema.character.caste.model;

import java.util.List;

import net.sf.anathema.lib.util.IIdentificate;

public interface ICaste {

  public String getId();

  public String getPrintName();

  public boolean supportsTrait(IIdentificate traitType);

  public List< ? extends IIdentificate> getTraitTypes();
}