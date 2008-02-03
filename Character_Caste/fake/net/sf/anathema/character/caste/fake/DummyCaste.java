package net.sf.anathema.character.caste.fake;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.caste.model.ICaste;
import net.sf.anathema.lib.util.IIdentificate;

public class DummyCaste implements ICaste {

  private final List<IIdentificate> traitTypes = new ArrayList<IIdentificate>();
  private final String casteId;
  
  public DummyCaste(String casteId) {
    this.casteId = casteId;
  }

  @Override
  public String getId() {
    return casteId;
  }

  @Override
  public String getPrintName() {
    return casteId + "PrintName"; //$NON-NLS-1$
  }

  @Override
  public List< ? extends IIdentificate> getTraitTypes() {
    return traitTypes;
  }

  @Override
  public boolean supportsTrait(IIdentificate traitType) {
    return traitTypes.contains(traitType);
  }

  public void addTraitType(IIdentificate identificate) {
    traitTypes.add(identificate);
  }
}