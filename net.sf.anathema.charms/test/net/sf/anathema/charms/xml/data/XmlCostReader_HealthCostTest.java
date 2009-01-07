package net.sf.anathema.charms.xml.data;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.cost.ResourceDto;
import net.sf.anathema.charms.xml.TestDocumentReader;

import org.junit.Before;
import org.junit.Test;

public class XmlCostReader_HealthCostTest {

  private CharmDto charmDto;

  @Before
  public void createCosts() throws Exception {
    charmDto = new CharmDto();
    readCosts("<cost><health cost=\"2\"/></cost>"); //$NON-NLS-1$
  }

  @Test
  public void hasLethalType() throws Exception {
    assertThat(getResourceDto().type, is("lethal_hl")); //$NON-NLS-1$
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