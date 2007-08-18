package net.sf.anathema.character.attributes.model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;
import net.sf.anathema.lib.xml.DocumentUtilities;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class AttributesPersister implements IModelPersister<IAttributes> {

  private static final String TAG_ATTRIBUTES = "attributes"; //$NON-NLS-1$
  private static final String ATTRIB_EXPERIENCED_VALUE = "experiencedValue"; //$NON-NLS-1$
  private static final String ATTRIB_CREATION_VALUE = "creationValue"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String TAG_TRAIT = "trait"; //$NON-NLS-1$

  @Override
  public IAttributes load(Document document) throws PersistenceException {
    final List<IBasicTrait> attributeTraits = new ArrayList<IBasicTrait>();
    for (Element traitElement : ElementUtilities.elements(document.getRootElement(), TAG_TRAIT)) {
      IIdentificate traitType = new Identificate(ElementUtilities.getRequiredAttrib(traitElement, ATTRIB_ID));
      IBasicTrait trait = new BasicTrait(traitType);
      attributeTraits.add(trait);
      trait.getCreationModel().setValue(ElementUtilities.getRequiredIntAttrib(traitElement, ATTRIB_CREATION_VALUE));
      if (traitElement.attribute(ATTRIB_EXPERIENCED_VALUE) != null) {
        int experiencedValue = ElementUtilities.getRequiredIntAttrib(traitElement, ATTRIB_EXPERIENCED_VALUE);
        trait.getExperiencedModel().setValue(experiencedValue);
      }
    }
    Attributes attributes = new Attributes(attributeTraits.toArray(new IBasicTrait[attributeTraits.size()]));
    attributes.setClean();
    return attributes;
  }

  @Override
  public void save(OutputStream stream, IAttributes item) throws IOException, PersistenceException {
    Element attributesElement = DocumentHelper.createElement(TAG_ATTRIBUTES);
    Document document = DocumentHelper.createDocument(attributesElement);
    for (IBasicTrait trait : item.getTraits()) {
      Element traitElement = attributesElement.addElement(TAG_TRAIT);
      traitElement.addAttribute(ATTRIB_ID, trait.getTraitType().getId());
      int creationValue = trait.getCreationModel().getValue();
      ElementUtilities.addAttribute(traitElement, ATTRIB_CREATION_VALUE, creationValue);
      int experiencedValue = trait.getExperiencedModel().getValue();
      if (experiencedValue > -1) {
        ElementUtilities.addAttribute(traitElement, ATTRIB_EXPERIENCED_VALUE, experiencedValue);
      }
    }
    DocumentUtilities.save(document, stream);
  }

  @Override
  public IAttributes createNew() {
    return Attributes.create(new AttributeTemplate().getGroups());
  }
}