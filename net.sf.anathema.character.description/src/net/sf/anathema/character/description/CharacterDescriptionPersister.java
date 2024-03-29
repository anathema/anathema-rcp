package net.sf.anathema.character.description;

import java.io.IOException;
import java.io.OutputStream;

import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;
import net.sf.anathema.basics.item.text.TextPersister;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.core.model.template.NullModelTemplate;
import net.sf.anathema.character.description.plugin.CharacterDescriptionPluginConstants;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.Element;

public class CharacterDescriptionPersister implements IModelPersister<NullModelTemplate, ICharacterDescription> {

  private static final String TAG_CHARACTERIZATION = "Characterization"; //$NON-NLS-1$
  private static final String TAG_CHARACTER_NAME = "Name"; //$NON-NLS-1$
  private static final String TAG_NOTES = "Notes"; //$NON-NLS-1$
  private static final String TAG_PERIPHRASE = "Periphrasis"; //$NON-NLS-1$
  private static final String TAG_PHYSICAL_DESCRIPTION = "PhysicalDescription"; //$NON-NLS-1$
  private static final String TAG_PLAYER = "Player"; //$NON-NLS-1$
  private static final String TAG_CONCEPT = "Concept"; //$NON-NLS-1$
  private static final String TAG_MODEL = "model"; //$NON-NLS-1$

  private final TextPersister textPersister = new TextPersister();

  public ICharacterDescription load(Document document, NullModelTemplate template) {
    ICharacterDescription description = createNew(template);
    if (document == null) {
      return description;
    }
    Element descriptionElement = document.getRootElement();
    textPersister.restoreTextualDescription(descriptionElement, TAG_CHARACTER_NAME, description.getName());
    textPersister.restoreTextualDescription(descriptionElement, TAG_PLAYER, description.getPlayer());
    textPersister.restoreTextualDescription(descriptionElement, TAG_CHARACTERIZATION, description.getCharacterization());
    textPersister.restoreTextualDescription(
        descriptionElement,
        TAG_PHYSICAL_DESCRIPTION,
        description.getPhysicalDescription());
    textPersister.restoreTextualDescription(descriptionElement, TAG_PERIPHRASE, description.getPeriphrasis());
    textPersister.restoreTextualDescription(descriptionElement, TAG_NOTES, description.getNotes());
    textPersister.restoreTextualDescription(descriptionElement, TAG_CONCEPT, description.getConcept());
    return description;
  }

  public void save(Element descriptionElement, ICharacterDescription description) {
    textPersister.saveTextualDescription(descriptionElement, TAG_CHARACTER_NAME, description.getName());
    textPersister.saveTextualDescription(descriptionElement, TAG_PLAYER, description.getPlayer());
    textPersister.saveTextualDescription(descriptionElement, TAG_CHARACTERIZATION, description.getCharacterization());
    textPersister.saveTextualDescription(
        descriptionElement,
        TAG_PHYSICAL_DESCRIPTION,
        description.getPhysicalDescription());
    textPersister.saveTextualDescription(descriptionElement, TAG_PERIPHRASE, description.getPeriphrasis());
    textPersister.saveTextualDescription(descriptionElement, TAG_NOTES, description.getNotes());
    textPersister.saveTextualDescription(descriptionElement, TAG_CONCEPT, description.getConcept());
  }

  @Override
  public void save(OutputStream stream, ICharacterDescription item) throws IOException, PersistenceException {
    Document document = new BundlePersistenceUtilities().createDocument(
        TAG_MODEL,
        CharacterDescriptionPluginConstants.PLUGIN_ID);
    save(document.getRootElement(), item);
    DocumentUtilities.save(document, stream);
  }

  @Override
  public ICharacterDescription createNew(NullModelTemplate template) {
    return new CharacterDescription();
  }
}