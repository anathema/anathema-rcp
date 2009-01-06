package net.sf.anathema.charms.xml;

import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Element;

public class TestDocumentReader implements IDocumentReader {

  private String xml;

  public void setXml(String xml) {
    this.xml = xml;
  }

  @Override
  public Element readDocument() throws Exception {
    return DocumentUtilities.read(xml).getRootElement();
  }

}
