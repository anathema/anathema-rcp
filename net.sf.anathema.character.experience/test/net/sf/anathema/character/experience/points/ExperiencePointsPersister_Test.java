package net.sf.anathema.character.experience.points;

import static net.sf.anathema.character.core.model.PersistenceTestingUtilities.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.item.persistence.IPluginDocumentFactory;
import net.sf.anathema.basics.item.persistence.UnversionedDocumentFactory;
import net.sf.anathema.character.experience.IExperiencePoints;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class ExperiencePointsPersister_Test {

  private ExperiencePointsPersister persister;

  @Before
  public void createPersister() throws Exception {
    IPluginDocumentFactory documentFactory = new UnversionedDocumentFactory();
    this.persister = new ExperiencePointsPersister(documentFactory);
  }

  @Test
  public void reloadsEmptyModel() throws Exception {
    IExperiencePoints savedPoints = new ExperiencePoints();
    IExperiencePoints loadedPoints = saveAndLoadWithoutTemplate(savedPoints, persister);
    assertThat(loadedPoints.getEntries().length, is(0));
  }

  @Test
  public void reloadsModelWithOneEntry() throws Exception {
    IExperiencePoints savedPoints = new ExperiencePoints();
    savedPoints.add(ExperienceEntry.CreateForPointsAndComment(3, "Indian Summer in Nexus"));
    IExperiencePoints loadedPoints = saveAndLoadWithoutTemplate(savedPoints, persister);
    assertThat(loadedPoints.getEntries().length, is(1));
    assertThat(loadedPoints.getEntries()[0].points, is(3));
    assertThat(loadedPoints.getEntries()[0].comment, is("Indian Summer in Nexus"));
  }

  @Test
  public void reloadsModelWithTwoEntries() throws Exception {
    IExperiencePoints savedPoints = new ExperiencePoints();
    savedPoints.add(ExperienceEntry.CreateForPointsAndComment(3, "Indian Summer in Nexus"));
    savedPoints.add(ExperienceEntry.CreateForPointsAndComment(2, "Schöner Winter bei Yurgen"));
    IExperiencePoints loadedPoints = saveAndLoadWithoutTemplate(savedPoints, persister);
    assertThat(loadedPoints.getEntries().length, is(2));
    assertThat(loadedPoints.getEntries()[0].points, is(3));
    assertThat(loadedPoints.getEntries()[0].comment, is("Indian Summer in Nexus"));
    assertThat(loadedPoints.getEntries()[1].points, is(2));
    assertThat(loadedPoints.getEntries()[1].comment, is("Schöner Winter bei Yurgen"));
  }
}