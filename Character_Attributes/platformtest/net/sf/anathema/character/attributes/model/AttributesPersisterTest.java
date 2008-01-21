package net.sf.anathema.character.attributes.model;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.status.DefaultStatus;
import net.sf.anathema.character.trait.status.FavoredStatus;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.junit.Before;
import org.junit.Test;

public class AttributesPersisterTest {

  private AttributesPersister persister;

  @Before
  public void createPersisterAndAttributes() throws Exception {
    persister = new AttributesPersister();
  }

  @Test
  public void favoredAttributeIsFavoredAfterLoad() throws Exception {
    ITraitCollectionModel attributes = persister.createNew(new AttributeTemplate(2));
    IBasicTrait favoredTrait = attributes.getTraits()[0];
    favoredTrait.getStatusManager().setStatus(new FavoredStatus());
    String favoredTraitId = favoredTrait.getTraitType().getId();
    ITraitCollectionModel loadedAttributes = saveAndLoadAttributes(attributes);
    assertTrue(loadedAttributes.getTrait(favoredTraitId).getStatusManager().getStatus() instanceof FavoredStatus);
  }

  @Test
  public void unfavoredAttributeIsUnfavoredAfterLoad() throws Exception {
    ITraitCollectionModel attributes = persister.createNew(new AttributeTemplate(2));
    IBasicTrait favoredTrait = attributes.getTraits()[0];
    String favoredTraitId = favoredTrait.getTraitType().getId();
    ITraitCollectionModel loadedAttributes = saveAndLoadAttributes(attributes);
    assertTrue(loadedAttributes.getTrait(favoredTraitId).getStatusManager().getStatus() instanceof DefaultStatus);
  }

  @Test
  public void earlyVersion1_0_0AttributesCanBeLoaded() throws Exception {
    String xmlString = "<model bundleVersion=\"1.0.0\">" //$NON-NLS-1$
        + "<trait id=\"Strength\" creationValue=\"1\"/>" //$NON-NLS-1$
        + "</model>"; //$NON-NLS-1$
    ITraitCollectionModel loadedAttributes = persister.load(DocumentUtilities.read(xmlString), new AttributeTemplate(0));
    assertIsInitialAttribute(loadedAttributes.getTrait("Strength")); //$NON-NLS-1$
    assertEquals(1, loadedAttributes.getTraits().length);
  }

  private void assertIsInitialAttribute(IBasicTrait trait) {
    assertTrue(trait.getStatusManager().getStatus() instanceof DefaultStatus);
    assertEquals(1, trait.getCreationModel().getValue());
    assertEquals(-1, trait.getExperiencedModel().getValue());
  }

  private ITraitCollectionModel saveAndLoadAttributes(ITraitCollectionModel attributes)
      throws IOException,
      PersistenceException {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    persister.save(stream, attributes);
    String xmlString = new String(stream.toByteArray());
    ITraitCollectionModel loadedAttributes = persister.load(DocumentUtilities.read(xmlString), new AttributeTemplate(0));
    return loadedAttributes;
  }
}