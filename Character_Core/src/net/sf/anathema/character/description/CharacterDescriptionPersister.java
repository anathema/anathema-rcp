package net.sf.anathema.character.description;

import java.io.IOException;
import java.io.OutputStream;

import net.sf.anathema.basics.item.persistence.ISingleFileItemPersister;
import net.sf.anathema.basics.item.text.TextPersister;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Document;
import org.dom4j.Element;

public class CharacterDescriptionPersister implements ISingleFileItemPersister<ICharacterDescription> {

  private static final String TAG_CHARACTERIZATION = "Characterization"; //$NON-NLS-1$
  private static final String TAG_CHARACTER_NAME = "CharacterName"; //$NON-NLS-1$
  private static final String TAG_NOTES = "Notes"; //$NON-NLS-1$
  private static final String TAG_PERIPHRASE = "Periphrasis"; //$NON-NLS-1$
  private static final String TAG_PHYSICAL_DESCRIPTION = "PhysicalDescription"; //$NON-NLS-1$
  private static final String TAG_PLAYER = "Player"; //$NON-NLS-1$
  private static final String TAG_CONCEPT = "Concept"; //$NON-NLS-1$
  private static final String TAG_DESCRIPTION = "Description"; //$NON-NLS-1$

  private final TextPersister textPersister = new TextPersister();

  public ICharacterDescription load(Document document) {
    Element parent = document.getRootElement();
    ICharacterDescription description = createNew();
    Element descriptionElement = parent.element(TAG_DESCRIPTION);
    if (descriptionElement == null) {
      return createNew();
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
    return description;
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

  @Override
  public void save(OutputStream stream, ICharacterDescription item) throws IOException, PersistenceException {
    // TODO Auto-generated method stub

  }

  @Override
  public ICharacterDescription createNew() {
    return new CharacterDescription();
  }
}