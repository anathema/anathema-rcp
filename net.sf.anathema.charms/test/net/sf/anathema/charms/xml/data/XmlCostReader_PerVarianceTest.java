package net.sf.anathema.charms.xml.data;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.cost.ResourceDto;
import net.sf.anathema.charms.xml.TestDocumentReader;

import org.junit.Before;
import org.junit.Test;

public class XmlCostReader_PerVarianceTest {

  private CharmDto charmDto;

  @Before
  public void createCosts() throws Exception {
    charmDto = new CharmDto();
  }

  @Test
  public void detectsLinearCostAtStartOfString() throws Exception {
    readCosts("<cost><health cost=\"2\" text=\"per ally\"/></cost>"); //$NON-NLS-1$
    assertThat(getResourceDto().baseDto, is(nullValue()));
    assertThat(getResourceDto().linearDto.get(0).unit, is("ally")); //$NON-NLS-1$
  }

  @Test
  public void ignoresPerWithinWords() throws Exception {
    readCosts("<cost><health cost=\"2\" text=\"super ally\"/></cost>"); //$NON-NLS-1$
    assertThat(getResourceDto().linearDto, is(nullValue()));
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