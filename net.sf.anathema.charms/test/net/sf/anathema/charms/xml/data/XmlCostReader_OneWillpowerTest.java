package net.sf.anathema.charms.xml.data;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.cost.ResourceDto;
import net.sf.anathema.charms.xml.TestDocumentReader;

import org.junit.Before;
import org.junit.Test;

public class XmlCostReader_OneWillpowerTest {

  private CharmDto charmDto;

  @Before
  public void createCosts() throws Exception {
    charmDto = new CharmDto();
    readCosts("<cost><willpower cost=\"1\"/></cost>"); //$NON-NLS-1$
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
  public void hasNoBaseCostsLinearCosts() throws Exception {
    assertThat(getResourceDto().linearDto, is(nullValue()));
  }

  @Test
  public void hasCostsOfTypeWillpower() throws Exception {
    assertThat(getResourceDto().type, is("willpower"));
  }

  @Test
  public void hasAmountOne() throws Exception {
    assertThat(getResourceDto().baseDto.amount, is(1));
  }

  @Test
  public void hasNoOrMore() throws Exception {
    assertThat(getResourceDto().baseDto.orMore, is(false));
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