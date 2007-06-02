package net.sf.anathema.basics.item.data;

import static net.sf.anathema.basics.item.data.TitledTextPersistenceConstants.*;

import org.dom4j.Element;

public class BasicsPersister {

  private final TextPersister textPersister = new TextPersister();

  public void save(ITitledText item, Element rootElement) {
    textPersister.saveTextualDescription(rootElement, TAG_NAME, item.getName());
    textPersister.saveTextualDescription(rootElement, TAG_SUMMARY, item.getContent());
  }

  public void load(Element parent, ITitledText item) {
    textPersister.restoreTextualDescription(parent, TAG_NAME, item.getName());
    textPersister.restoreTextualDescription(parent, TAG_SUMMARY, item.getContent());
  }
}