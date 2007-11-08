package net.sf.anathema.character.description.importwizard;

import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;
import net.sf.anathema.character.description.plugin.CharacterDescriptionPluginConstants;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class DescriptionConverter {

  private static final String TAG_PERIPHRASIS = "Periphrasis"; //$NON-NLS-1$
  private static final String TAG_PERIPHRASE = "Periphrase"; //$NON-NLS-1$
  private static final String TAG_MODEL = "model"; //$NON-NLS-1$
  private static final String TAG_DESCRIPTION = "Description"; //$NON-NLS-1$
  private static final String TAG_CHARACTER_NAME = "CharacterName"; //$NON-NLS-1$
  private static final String TAG_NAME = "Name"; //$NON-NLS-1$

  public Document convert(Document document) {
    Element element = document.getRootElement().element(TAG_DESCRIPTION).createCopy(TAG_MODEL);
    BundlePersistenceUtilities.addBundleVersionAttribute(element, CharacterDescriptionPluginConstants.PLUGIN_ID);
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