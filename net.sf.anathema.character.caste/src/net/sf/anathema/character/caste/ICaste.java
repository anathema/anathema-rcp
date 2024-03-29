package net.sf.anathema.character.caste;

import java.util.List;

import net.sf.anathema.lib.util.IIdentificate;

public interface ICaste extends IIdentificate {

  public String getPrintName();

  public boolean supportsTrait(IIdentificate traitType);

  public List< ? extends IIdentificate> getTraitTypes();
}