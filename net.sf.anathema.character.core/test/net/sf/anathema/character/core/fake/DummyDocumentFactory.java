package net.sf.anathema.character.core.fake;

import net.disy.commons.core.creation.IFactory;

import org.dom4j.Document;

public class DummyDocumentFactory implements IFactory<Document, RuntimeException> {

  public Document document;

  @Override
  public Document createInstance() throws RuntimeException {
    return document;
  }
}