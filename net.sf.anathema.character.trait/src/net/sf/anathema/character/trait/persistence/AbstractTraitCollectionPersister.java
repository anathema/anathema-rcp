package net.sf.anathema.character.trait.persistence;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.core.model.template.IModelTemplate;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.plugin.CharacterTraitPlugin;
import net.sf.anathema.character.trait.status.FavoredStatus;
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
  public M load(Document document, T modelTemplate) throws PersistenceException {
    final List<IBasicTrait> traits = new ArrayList<IBasicTrait>();
    for (Element traitElement : ElementUtilities.elements(document.getRootElement(), TAG_TRAIT)) {
      IBasicTrait trait = loadTrait(traitElement);
      traits.add(trait);
      boolean favored = ElementUtilities.getBooleanAttribute(traitElement, ATTRIB_FAVORED, false);
      if (favored) {
        trait.getStatusManager().setStatus(new FavoredStatus());
      }
    }
    M model = createModelFor(traits.toArray(new IBasicTrait[traits.size()]));
    for (Element traitElement : ElementUtilities.elements(document.getRootElement(), TAG_TRAIT)) {
      for (Element subTraitElement: ElementUtilities.elements(traitElement, TAG_TRAIT)) {
        IBasicTrait subTrait = loadTrait(subTraitElement);
        String traitId = ElementUtilities.getRequiredAttrib(traitElement, ATTRIB_ID);
        model.addSubTrait(traitId, subTrait);
      }
    }
    model.setClean();
    return model;
  }

  private IBasicTrait loadTrait(Element traitElement) throws PersistenceException {
    IIdentificate traitType = new Identificate(ElementUtilities.getRequiredAttrib(traitElement, ATTRIB_ID));
    IBasicTrait trait = new BasicTrait(traitType);
    trait.getCreationModel().setValue(ElementUtilities.getRequiredIntAttrib(traitElement, ATTRIB_CREATION_VALUE));
    if (traitElement.attribute(ATTRIB_EXPERIENCED_VALUE) != null) {
      int experiencedValue = ElementUtilities.getRequiredIntAttrib(traitElement, ATTRIB_EXPERIENCED_VALUE);
      trait.getExperiencedModel().setValue(experiencedValue);
    }
    return trait;
  }

  protected abstract M createModelFor(IBasicTrait[] array);

  @Override
  public void save(OutputStream stream, M item) throws IOException, PersistenceException {
    Document document = new BundlePersistenceUtilities().createVersionedDocument(
        TAG_MODEL,
        CharacterTraitPlugin.PLUGIN_ID);
    Element root = document.getRootElement();
    for (IBasicTrait trait : item.getAllTraits()) {
      Element traitElement = saveTrait(root, trait);
      ElementUtilities.addAttribute(
          traitElement,
          ATTRIB_FAVORED,
          trait.getStatusManager().getStatus() instanceof FavoredStatus);
      for (IBasicTrait subTrait : item.getSubTraits(trait.getTraitType().getId())) {
        saveTrait(traitElement, subTrait);
      }
    }
    DocumentUtilities.save(document, stream);
  }

  private Element saveTrait(Element root, IBasicTrait trait) {
    Element traitElement = root.addElement(TAG_TRAIT);
    traitElement.addAttribute(ATTRIB_ID, trait.getTraitType().getId());
    int creationValue = trait.getCreationModel().getValue();
    ElementUtilities.addAttribute(traitElement, ATTRIB_CREATION_VALUE, creationValue);
    int experiencedValue = trait.getExperiencedModel().getValue();
    if (experiencedValue > -1) {
      ElementUtilities.addAttribute(traitElement, ATTRIB_EXPERIENCED_VALUE, experiencedValue);
    }
    return traitElement;
  }
}