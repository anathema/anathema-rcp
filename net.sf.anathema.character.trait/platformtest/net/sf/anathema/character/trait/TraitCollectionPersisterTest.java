package net.sf.anathema.character.trait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.FavorizationTemplate;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.model.IFavorizationTemplate;
import net.sf.anathema.character.trait.model.ITraitCollectionTemplate;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;
import net.sf.anathema.character.trait.persistence.StaticTraitGroupTemplate;
import net.sf.anathema.character.trait.persistence.TraitCollectionPersister;
import net.sf.anathema.character.trait.status.DefaultStatus;
import net.sf.anathema.character.trait.status.FavoredStatus;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.junit.Before;
import org.junit.Test;

public class TraitCollectionPersisterTest {

  private static final String TRAIT2 = "Trait2"; //$NON-NLS-1$
  private static final String TRAIT1 = "Trait1"; //$NON-NLS-1$
  private TraitCollectionPersister persister;

  @Before
  public void createPersister() throws Exception {
    persister = new TraitCollectionPersister();
  }

  private ITraitCollectionTemplate createTemplate() {
    return new ITraitCollectionTemplate() {

      @Override
      public ITraitGroupTemplate getGroupTemplate() {
        final TraitGroup[] groups = new TraitGroup[] {new TraitGroup("Gruppe", null, TRAIT1, TRAIT2)}; //$NON-NLS-1$
        return new StaticTraitGroupTemplate(groups);
      }

      @Override
      public IFavorizationTemplate getFavorizationTemplate() {
        return new FavorizationTemplate(5);
      }
    };
  }

  @Test
  public void favoredTraitIsFavoredAfterLoad() throws Exception {
    ITraitCollectionModel traits = persister.createNew(createTemplate());
    IBasicTrait favoredTrait = traits.getTraits()[0];
    favoredTrait.getStatusManager().setStatus(new FavoredStatus());
    String favoredTraitId = favoredTrait.getTraitType().getId();
    ITraitCollectionModel loaded = saveAndLoad(traits);
    assertTrue(loaded.getTrait(favoredTraitId).getStatusManager().getStatus() instanceof FavoredStatus);
  }

  @Test
  public void unfavoredTraitIsUnfavoredAfterLoad() throws Exception {
    ITraitCollectionModel attributes = persister.createNew(createTemplate());
    IBasicTrait favoredTrait = attributes.getTraits()[0];
    String favoredTraitId = favoredTrait.getTraitType().getId();
    ITraitCollectionModel loaded = saveAndLoad(attributes);
    assertTrue(loaded.getTrait(favoredTraitId).getStatusManager().getStatus() instanceof DefaultStatus);
  }

  @Test
  public void earlyVersion1_0_0TraitsCanBeLoaded() throws Exception {
    String xmlString = "<model bundleVersion=\"1.0.0\">" //$NON-NLS-1$
        + "<trait id=\"Trait1\" creationValue=\"1\"/>" //$NON-NLS-1$
        + "</model>"; //$NON-NLS-1$
    ITraitCollectionModel loaded = persister.load(DocumentUtilities.read(xmlString), createTemplate());
    assertIsTrait(loaded.getTrait(TRAIT1), 1);
    assertEquals(1, loaded.getTraits().length);
  }

  private void assertIsTrait(IBasicTrait trait, int creationValue) {
    assertTrue(trait.getStatusManager().getStatus() instanceof DefaultStatus);
    assertEquals(creationValue, trait.getCreationModel().getValue());
    assertEquals(-1, trait.getExperiencedModel().getValue());
  }

  private ITraitCollectionModel saveAndLoad(ITraitCollectionModel attributes)
      throws IOException,
      PersistenceException {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    persister.save(stream, attributes);
    String xmlString = new String(stream.toByteArray());
    return persister.load(DocumentUtilities.read(xmlString), createTemplate());
  }
}