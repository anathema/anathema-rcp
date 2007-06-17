package net.sf.anathema.character.attributes;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.item.persistence.ISingleFileItemPersister;
import net.sf.anathema.character.core.trait.IPersistenceTrait;
import net.sf.anathema.character.core.trait.ITrait;
import net.sf.anathema.character.core.trait.PersistenceTrait;
import net.sf.anathema.character.core.trait.Trait;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;
import net.sf.anathema.lib.xml.DocumentUtilities;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class AttributesPersister implements ISingleFileItemPersister<IAttributes> {

  private static final String TAG_ATTRIBUTES = "attributes"; //$NON-NLS-1$
  private static final String ATTRIB_EXPERIENCED_VALUE = "experiencedValue"; //$NON-NLS-1$
  private static final String ATTRIB_CREATION_VALUE = "creationValue"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String TAG_TRAIT = "trait"; //$NON-NLS-1$

  @Override
  public IAttributes load(Document document) throws PersistenceException {
    final List<ITrait> attributeTraits = new ArrayList<ITrait>();
    for (Element traitElement : ElementUtilities.elements(document.getRootElement(), TAG_TRAIT)) {
      IIdentificate traitType = new Identificate(ElementUtilities.getRequiredAttrib(traitElement, ATTRIB_ID));
      ITrait creationTrait = new Trait(traitType);
      ITrait experiencedTrait = new Trait(traitType);
      attributeTraits.add(new PersistenceTrait(creationTrait, experiencedTrait));
      creationTrait.setValue(ElementUtilities.getRequiredIntAttrib(traitElement, ATTRIB_CREATION_VALUE));
      if (traitElement.attribute(ATTRIB_EXPERIENCED_VALUE) != null) {
        experiencedTrait.setValue(ElementUtilities.getRequiredIntAttrib(traitElement, ATTRIB_EXPERIENCED_VALUE));
      }
    }
    Attributes attributes = new Attributes(attributeTraits.toArray(new ITrait[attributeTraits.size()]));
    attributes.setClean();
    return attributes;
  }

  @Override
  public void save(OutputStream stream, IAttributes item) throws IOException, PersistenceException {
    Element attributesElement = DocumentHelper.createElement(TAG_ATTRIBUTES);
    Document document = DocumentHelper.createDocument(attributesElement);
    for (ITrait trait : item.getTraits()) {
      IPersistenceTrait persistenceTrait = (IPersistenceTrait) trait;
      Element traitElement = attributesElement.addElement(TAG_TRAIT);
      traitElement.addAttribute(ATTRIB_ID, trait.getTraitType().getId());
      int creationValue = persistenceTrait.getCreationValue();
      ElementUtilities.addAttribute(traitElement, ATTRIB_CREATION_VALUE, creationValue);
      int experiencedValue = persistenceTrait.getExperiencedValue();
      // TODO vernünftige Regel für experiencedValue isSet
      if (experiencedValue > creationValue) {
        ElementUtilities.addAttribute(traitElement, ATTRIB_EXPERIENCED_VALUE, experiencedValue);
      }
    }
    DocumentUtilities.save(document, stream);
  }

  @Override
  public IAttributes createNew() {
    throw new UnsupportedOperationException();
  }
}