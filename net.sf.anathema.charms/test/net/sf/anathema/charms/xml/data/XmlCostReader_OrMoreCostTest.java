package net.sf.anathema.charms.xml.data;

import static net.sf.anathema.test.hamcrest.AnathemaMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.cost.ResourceDto;
import net.sf.anathema.charms.xml.TestDocumentReader;

import org.junit.Before;
import org.junit.Test;

public class XmlCostReader_OrMoreCostTest {

  private CharmDto charmDto;

  @Before
  public void createCosts() throws Exception {
    charmDto = new CharmDto();
    readCosts("<cost><essence cost=\"2\" text=\"or more\"/></cost>"); //$NON-NLS-1$
  }

  @Test
  public void hasBaseCostWithSetOrMore() throws Exception {
    assertThat(getResourceDto().baseDto.orMore, is(true));
  }

  @Test
  public void hasNoLinearCosts() throws Exception {
    assertThat(getResourceDto().linearDto, is(empty()));
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