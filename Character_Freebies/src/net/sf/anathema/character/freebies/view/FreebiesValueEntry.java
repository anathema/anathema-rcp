package net.sf.anathema.character.freebies.view;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.freebies.configuration.internal.IFreebiesConfiguration;
import net.sf.anathema.character.freebies.configuration.internal.IFreebiesResult;
import net.sf.anathema.view.valuelist.IValueEntry;

public final class FreebiesValueEntry implements IValueEntry {
  private final IFreebiesConfiguration configuration;
  private final ICharacterId id;

  FreebiesValueEntry(IFreebiesConfiguration configuration, ICharacterId id) {
    this.configuration = configuration;
    this.id = id;
  }

  @Override
  public String getValue() {
    IFreebiesResult freebies = configuration.getFreebies(id);
    if (!freebies.isValid()) {
      return "Missing"; 
    }
    String points = freebies.getPoints();
    String credits = freebies.getCredit();
    return points + " / " + credits;
  }

  @Override
  public String getDisplayName() {
    return configuration.getName();
  }
}