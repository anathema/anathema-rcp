package net.sf.anathema.character.importwizard;

import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class CharacterDescriptionConverter {

  private static final String TAG_PERIPHRASIS = "Periphrasis"; //$NON-NLS-1$
  private static final String TAG_PERIPHRASE = "Periphrase"; //$NON-NLS-1$
  private static final String TAG_MODEL = "model"; //$NON-NLS-1$
  private static final String TAG_DESCRIPTION = "Description"; //$NON-NLS-1$
  private static final String TAG_CHARACTER_NAME = "CharacterName"; //$NON-NLS-1$
  private static final String TAG_NAME = "Name"; //$NON-NLS-1$

  public Document convert(Document document) {
    Element element = document.getRootElement().element(TAG_DESCRIPTION).createCopy(TAG_MODEL);
    // TODO Get constant character_core.id
    BundlePersistenceUtilities.addBundleVersionAttribute(element, "net.sf.anathema.character.core");
    renameElement(element, TAG_CHARACTER_NAME, TAG_NAME);
    renameElement(element, TAG_PERIPHRASE, TAG_PERIPHRASIS);
    return DocumentHelper.createDocument(element);
  }

  private void renameElement(Element parent, String originalName, String newName) {
    Element nameElement = parent.element(originalName);
    if (nameElement != null) {
      nameElement.setName(newName);
    }
  }
}