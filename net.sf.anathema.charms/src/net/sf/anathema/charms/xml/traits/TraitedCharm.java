package net.sf.anathema.charms.xml.traits;

import net.sf.anathema.charms.traits.CharmTraits;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class TraitedCharm implements ITraitedCharm {

  private static final String ATTRIB_PRIMARY_MINIMUM = "primaryMinimum"; //$NON-NLS-1$
  private static final String ATTRIB_ESSENCE_MINIMUM = "essenceMinimum"; //$NON-NLS-1$
  private static final String ATTRIB_CHARM_ID = "charmId"; //$NON-NLS-1$
  private final Element charmTraitsElement;

  public TraitedCharm(Element charmTraitsElement) {
    this.charmTraitsElement = charmTraitsElement;
  }

  @Override
  public CharmTraits createDto() throws PersistenceException {
    CharmTraits charmTraits = new CharmTraits();
    charmTraits.essenceMinimum = getValue(ATTRIB_ESSENCE_MINIMUM);
    charmTraits.primaryMinimum = getValue(ATTRIB_PRIMARY_MINIMUM);
    return charmTraits;
  }

  private int getValue(String attributeName) throws PersistenceException {
    return ElementUtilities.getIntAttrib(charmTraitsElement, attributeName, 1);
  }

  @Override
  public boolean hasId(ICharmId charmId) {
    String charmIdAttribute = charmTraitsElement.attributeValue(ATTRIB_CHARM_ID);
    String charmIdPattern = charmId.getIdPattern();
    return charmIdAttribute.equals(charmIdPattern);
  }
}