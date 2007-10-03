package net.sf.anathema.character.freebies.view;

import java.text.MessageFormat;

import net.sf.anathema.character.core.character.ICharacterId;
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
      return Messages.FreebiesValueEntry_MissingEntry; 
    }
    String points = freebies.getPoints();
    String credits = freebies.getCredit();
    return MessageFormat.format(Messages.FreebiesValueEntry_EntryMessageFormat, points, credits);
  }

  @Override
  public String getDisplayName() {
    return configuration.getName();
  }
}