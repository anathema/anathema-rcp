package net.sf.anathema.character.trait.collection;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.anathema.character.trait.model.IFavorizationTemplate;
import net.sf.anathema.lib.util.IIdentificate;

public class FavorizationTemplate implements IFavorizationTemplate {

  private final int favoredCount;
  private final Collection<String> requiredFavored;

  public FavorizationTemplate(int favoredCount) {
    this(favoredCount, new ArrayList<String>());
  }

  public FavorizationTemplate(int favoredCount, Collection<String> requiredFavored) {
    this.favoredCount = favoredCount;
    this.requiredFavored = requiredFavored;
  }
  
  @Override
  public int getAllowedFavored() {
    return favoredCount;
  }

  @Override
  public boolean isRequiredFavored(IIdentificate traitType) {
    return requiredFavored.contains(traitType.getId());
  }
}