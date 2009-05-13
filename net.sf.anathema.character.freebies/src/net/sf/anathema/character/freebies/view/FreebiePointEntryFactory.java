package net.sf.anathema.character.freebies.view;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.internal.FreebiesConfigurationExtensionPoint;
import net.sf.anathema.character.freebies.configuration.internal.IFreebiesConfiguration;
import net.sf.anathema.lib.collection.CollectionUtilities;
import net.sf.anathema.view.valuelist.IValueEntry;

public class FreebiePointEntryFactory implements IFreebiePointEntryFactory {

  private final IFreebiesConfigurationsProvider configurationProvider;

  public FreebiePointEntryFactory() {
    this(new FreebiesConfigurationExtensionPoint(new CreditManager()));
  }

  public FreebiePointEntryFactory(IFreebiesConfigurationsProvider configurationProvider) {
    this.configurationProvider = configurationProvider;
  }

  @Override
  public List<IValueEntry> create(final ICharacterId id) {
    if (id == null) {
      return new ArrayList<IValueEntry>();
    }
    IFreebiesConfiguration[] freebiesConfigurations = configurationProvider.get(id);
    return CollectionUtilities.transform(
        freebiesConfigurations,
        new ITransformer<IFreebiesConfiguration, IValueEntry>() {
          @Override
          public IValueEntry transform(final IFreebiesConfiguration configuration) {
            return new FreebiesValueEntry(configuration, id);
          }
        });
  }
}