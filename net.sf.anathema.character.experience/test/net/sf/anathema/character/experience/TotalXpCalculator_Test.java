package net.sf.anathema.character.experience;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.core.fake.ModelContainerObjectMother;
import net.sf.anathema.character.experience.points.ExperienceEntry;
import net.sf.anathema.character.experience.points.ExperiencePoints;

import org.junit.Before;
import org.junit.Test;

public class TotalXpCalculator_Test {

  private ExperiencePoints experiencePoints;
  private TotalXpCalculator calculator;

  @Before
  public void createCalculator() throws Exception {
    this.experiencePoints = new ExperiencePoints();
    this.calculator = new TotalXpCalculator(ModelContainerObjectMother.CreateForModelId(
        IExperiencePoints.MODEL_ID,
        experiencePoints));
  }

  @Test
  public void returnsZeroXpWithoutEntries() throws Exception {
    assertThat(calculator.calculate(), is(0));
  }

  @Test
  public void returnsPointsOfEntryForSingleEntry() throws Exception {
    experiencePoints.add(ExperienceEntry.CreateForPointsAndComment(12, null));
    assertThat(calculator.calculate(), is(12));
  }

  @Test
  public void returnsSumOfPointsOfEntryForMultipleEntries() throws Exception {
    experiencePoints.add(ExperienceEntry.CreateForPointsAndComment(12, null));
    experiencePoints.add(ExperienceEntry.CreateForPointsAndComment(6, null));
    assertThat(calculator.calculate(), is(18));
  }
}