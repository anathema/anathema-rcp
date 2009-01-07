package net.sf.anathema.charms.xml.data;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.cost.ResourceDto;
import net.sf.anathema.charms.xml.TestDocumentReader;

import org.junit.Before;
import org.junit.Test;

public class XmlCostReader_OneWillpowerPerArrowTest {

  private CharmDto charmDto;

  @Before
  public void createCosts() throws Exception {
    charmDto = new CharmDto();
    readCosts("<cost><willpower cost=\"1\" text=\" per arrow\"/></cost>"); //$NON-NLS-1$
  }

  @Test
  public void hasOnlyOneCostAlternative() throws Exception {
    assertThat(charmDto.costs.size(), is(1));
  }

  @Test
  public void hasOnlyOneCostResource() throws Exception {
    assertThat(charmDto.costs.get(0).resources.size(), is(1));
  }

  @Test
  public void hasNoBaseCosts() throws Exception {
    assertThat(getResourceDto().baseDto, is(nullValue()));
  }

  @Test
  public void hasCostsOfTypeWillpower() throws Exception {
    assertThat(getResourceDto().type, is("willpower")); //$NON-NLS-1$
  }

  @Test
  public void hasAmountOne() throws Exception {
    assertThat(getResourceDto().linearDto.get(0).amount, is(1));
  }

  @Test
  public void hasUnitArrow() throws Exception {
    assertThat(getResourceDto().linearDto.get(0).unit, is("arrow")); //$NON-NLS-1$
  }

  private ResourceDto getResourceDto() {
    return charmDto.costs.get(0).resources.get(0);
  }

  private void readCosts(String xml) throws Exception {
    TestDocumentReader reader = new TestDocumentReader();
    reader.setXml(xml);
    new XmlCostReader(reader.readDocument(), charmDto).read();
  }
}