package net.sf.anathema.character.experience.points;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class ExperiencePoints_Test {

  private ExperiencePoints experiencePoints;

  @Before
  public void createModel() throws Exception {
    this.experiencePoints = new ExperiencePoints();
  }

  @Test
  public void isDirtyAfterCreation() throws Exception {
    assertThat(experiencePoints.isDirty(), is(true));
  }

  @Test
  public void isDirtyAfterAddingAnEntry() throws Exception {
    experiencePoints.setClean();
    experiencePoints.add(ExperienceEntry.CreateForPointsAndComment(2, "Shit happens"));
    assertThat(experiencePoints.isDirty(), is(true));
  }
}