package net.sf.anathema.charms.display;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.data.cost.BaseDto;
import net.sf.anathema.charms.data.cost.LinearDto;
import net.sf.anathema.charms.data.cost.ResourceDto;

import org.junit.Before;
import org.junit.Test;

public class DisplayResource_MotesTest {

  private ResourceDto resourceDto;

  @Before
  public void createMotesDto() {
    resourceDto = new ResourceDto();
    resourceDto.type = "motes"; //$NON-NLS-1$
  }

  @Test
  public void createsDisplayWithWithAmountAndShortResource() throws Exception {
    resourceDto.baseDto = new BaseDto();
    resourceDto.baseDto.amount = 2;
    resourceDto.baseDto.orMore = false;
    assertThatDisplayedResourceIs("2m"); //$NON-NLS-1$
  }

  @Test
  public void addsPlusToDisplayName() throws Exception {
    resourceDto.baseDto = new BaseDto();
    resourceDto.baseDto.amount = 3;
    resourceDto.baseDto.orMore = true;
    assertThatDisplayedResourceIs("3m+"); //$NON-NLS-1$
  }

  @Test
  public void createsDisplayWithWithAmountAndShortResourcePerUnit() throws Exception {
    resourceDto.linearDto = new LinearDto();
    resourceDto.linearDto.amount = 4;
    resourceDto.linearDto.unit = "Affe"; //$NON-NLS-1$
    assertThatDisplayedResourceIs("4m/Affe"); //$NON-NLS-1$
  }

  @Test
  public void combinesBaseAndLinearAmount() throws Exception {
    resourceDto.baseDto = new BaseDto();
    resourceDto.baseDto.amount = 2;
    resourceDto.baseDto.orMore = false;
    resourceDto.linearDto = new LinearDto();
    resourceDto.linearDto.amount = 4;
    resourceDto.linearDto.unit = "die"; //$NON-NLS-1$
    assertThatDisplayedResourceIs("2m+4m/die"); //$NON-NLS-1$
  }

  private void assertThatDisplayedResourceIs(String expectedDisplay) {
    assertThat(new DisplayResource(resourceDto).get(), is(expectedDisplay));
  }
}