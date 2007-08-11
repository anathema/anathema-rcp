package net.sf.anathema.character.freebies.view;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.freebies.configuration.internal.CreditManager;
import net.sf.anathema.character.freebies.configuration.internal.FreebiesConfigurationExtensionPoint;
import net.sf.anathema.character.freebies.configuration.internal.IFreebiesConfiguration;
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
  public IValueEntry[] create(final ICharacterId id) {
    if (id == null) {
      return new IValueEntry[0];
    }
    IFreebiesConfiguration[] freebiesConfigurations = configurationProvider.get(id);
    return ArrayUtilities.transform(
        freebiesConfigurations,
        IValueEntry.class,
        new ITransformer<IFreebiesConfiguration, IValueEntry>() {
          @Override
          public IValueEntry transform(final IFreebiesConfiguration configuration) {
            return new FreebiesValueEntry(configuration, id);
          }
        });
  }
}