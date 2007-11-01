package net.sf.anathema.character.trait.persistence;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.core.model.template.IModelTemplate;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.plugin.CharacterTraitPlugin;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;
import net.sf.anathema.lib.xml.DocumentUtilities;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Document;
import org.dom4j.Element;

public abstract class AbstractTraitCollectionPersister<T extends IModelTemplate, M extends ITraitCollectionModel> implements
    IModelPersister<T, M> {

  private static final String TAG_MODEL = "model"; //$NON-NLS-1$
  private static final String ATTRIB_EXPERIENCED_VALUE = "experiencedValue"; //$NON-NLS-1$
  private static final String ATTRIB_FAVORED = "favored"; //$NON-NLS-1$
  private static final String ATTRIB_CREATION_VALUE = "creationValue"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String TAG_TRAIT = "trait"; //$NON-NLS-1$

  @Override
  public M load(Document document) throws PersistenceException {
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
      BooleanModel favoredModel = trait.getFavoredModel();
      boolean favored = ElementUtilities.getBooleanAttribute(traitElement, ATTRIB_FAVORED, favoredModel.getValue());
      favoredModel.setValue(favored);
    }
    M model = createModelFor(attributeTraits.toArray(new IBasicTrait[attributeTraits.size()]));
    model.setClean();
    return model;
  }

  protected abstract M createModelFor(IBasicTrait[] array);

  @Override
  public void save(OutputStream stream, M item) throws IOException, PersistenceException {
    Document document = BundlePersistenceUtilities.createVersionedDocument(TAG_MODEL, CharacterTraitPlugin.PLUGIN_ID);
    Element root = document.getRootElement();
    for (IBasicTrait trait : item.getTraits()) {
      Element traitElement = root.addElement(TAG_TRAIT);
      traitElement.addAttribute(ATTRIB_ID, trait.getTraitType().getId());
      int creationValue = trait.getCreationModel().getValue();
      ElementUtilities.addAttribute(traitElement, ATTRIB_CREATION_VALUE, creationValue);
      int experiencedValue = trait.getExperiencedModel().getValue();
      if (experiencedValue > -1) {
        ElementUtilities.addAttribute(traitElement, ATTRIB_EXPERIENCED_VALUE, experiencedValue);
      }
      ElementUtilities.addAttribute(traitElement, ATTRIB_FAVORED, trait.getFavoredModel().getValue());
    }
    DocumentUtilities.save(document, stream);
  }
}