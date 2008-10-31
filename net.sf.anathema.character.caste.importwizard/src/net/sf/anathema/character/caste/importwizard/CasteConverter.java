package net.sf.anathema.character.caste.importwizard;

import net.sf.anathema.character.caste.persistence.CasteModelPersister;
import net.sf.anathema.character.importwizard.IDocumentConverter;
import net.sf.anathema.lib.util.Identificate;

import org.dom4j.Document;
import org.dom4j.Element;

public class CasteConverter implements IDocumentConverter {

  private static final String ATTRIB_TYPE = "type"; //$NON-NLS-1$
  private static final String TAG_STATISTICS = "Statistics"; //$NON-NLS-1$
  private static final String TAG_CHARACTERCONCEPT = "CharacterConcept"; //$NON-NLS-1$
  private static final String TAG_CASTE = "Caste"; //$NON-NLS-1$

  public Document convert(Document document) {
    Element casteElement = document.getRootElement().element(TAG_STATISTICS).element(TAG_CHARACTERCONCEPT).element(
        TAG_CASTE);
    String caste = null;
    if (casteElement != null) {
      caste = casteElement.attributeValue(ATTRIB_TYPE);
    }
    return new CasteModelPersister().createCasteDocument(new Identificate(caste));
  }
}