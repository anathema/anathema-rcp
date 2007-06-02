package net.sf.anathema.character.description;

import net.sf.anathema.basics.item.data.TextPersister;

import org.dom4j.Element;

public class CharacterDescriptionPersister {

  private static final String TAG_CHARACTERIZATION = "Characterization"; //$NON-NLS-1$
  private static final String TAG_CHARACTER_NAME = "CharacterName"; //$NON-NLS-1$
  private static final String TAG_NOTES = "Notes"; //$NON-NLS-1$
  private static final String TAG_PERIPHRASE = "Periphrasis"; //$NON-NLS-1$
  private static final String TAG_PHYSICAL_DESCRIPTION = "PhysicalDescription"; //$NON-NLS-1$
  private static final String TAG_PLAYER = "Player"; //$NON-NLS-1$
  private static final String TAG_CONCEPT = "Concept"; //$NON-NLS-1$
  private static final String TAG_DESCRIPTION = "Description"; //$NON-NLS-1$

  private final TextPersister textPersister = new TextPersister();

  public void load(Element parent, ICharacterDescription description) {
    Element descriptionElement = parent.element(TAG_DESCRIPTION);
    if (descriptionElement == null) {
      return;
    }
    textPersister.restoreTextualDescription(descriptionElement, TAG_CHARACTER_NAME, description.getName());
    textPersister.restoreTextualDescription(descriptionElement, TAG_PLAYER, description.getPlayer());
    textPersister.restoreTextualDescription(descriptionElement, TAG_CHARACTERIZATION, description.getCharacterization());
    textPersister.restoreTextualDescription(
        descriptionElement,
        TAG_PHYSICAL_DESCRIPTION,
        description.getPhysicalDescription());
    textPersister.restoreTextualDescription(descriptionElement, TAG_PERIPHRASE, description.getPeriphrase());
    textPersister.restoreTextualDescription(descriptionElement, TAG_NOTES, description.getNotes());
    textPersister.restoreTextualDescription(descriptionElement, TAG_CONCEPT, description.getConcept());
  }

  public void save(Element parent, ICharacterDescription description) {
    Element descriptionElement = parent.addElement(TAG_DESCRIPTION);
    textPersister.saveTextualDescription(descriptionElement, TAG_CHARACTER_NAME, description.getName());
    textPersister.saveTextualDescription(descriptionElement, TAG_PLAYER, description.getPlayer());
    textPersister.saveTextualDescription(descriptionElement, TAG_CHARACTERIZATION, description.getCharacterization());
    textPersister.saveTextualDescription(
        descriptionElement,
        TAG_PHYSICAL_DESCRIPTION,
        description.getPhysicalDescription());
    textPersister.saveTextualDescription(descriptionElement, TAG_PERIPHRASE, description.getPeriphrase());
    textPersister.saveTextualDescription(descriptionElement, TAG_NOTES, description.getNotes());
    textPersister.saveTextualDescription(descriptionElement, TAG_CONCEPT, description.getConcept());
  }
}