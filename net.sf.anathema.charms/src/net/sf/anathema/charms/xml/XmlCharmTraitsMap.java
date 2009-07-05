package net.sf.anathema.charms.xml;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.charms.IPluginConstants;
import net.sf.anathema.charms.traits.CharmTraits;
import net.sf.anathema.charms.traits.IExecutableCharmTraitMap;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.charms.xml.traits.ITraitedCharm;
import net.sf.anathema.charms.xml.traits.TraitedCharmCollection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class XmlCharmTraitsMap implements IExecutableCharmTraitMap {

  private static final String ATTRIB_RESOURCE = "resource"; //$NON-NLS-1$
  private final List<Iterable<ITraitedCharm>> charmCollections = new ArrayList<Iterable<ITraitedCharm>>();

  @Override
  public CharmTraits getTraits(ICharmId charmId) {
    for (Iterable<ITraitedCharm> charmCollection : charmCollections) {
      for (ITraitedCharm charm : charmCollection) {
        if (charm.hasId(charmId)) {
          try {
            return charm.createDto();
          }
          catch (Exception e) {
            new Logger(IPluginConstants.PLUGIN_ID).error("Error reading charm traits from xml.", e);
          }
        }
      }
    }
    return null;
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    for (IConfigurationElement element : config.getChildren()) {
      addCharmCollection(new TraitedCharmCollection(element.getAttribute(ATTRIB_RESOURCE), config.getContributor()));
    }
  }

  protected void addCharmCollection(Iterable<ITraitedCharm> collection) {
    charmCollections.add(collection);
  }
}