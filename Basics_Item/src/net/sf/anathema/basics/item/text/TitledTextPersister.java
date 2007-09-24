package net.sf.anathema.basics.item.text;

import java.io.IOException;
import java.io.OutputStream;

import net.sf.anathema.basics.item.persistence.ISingleFileItemPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class TitledTextPersister implements ISingleFileItemPersister<ITitledText> {

  private static final String TAG_SUMMARY = "Summary"; //$NON-NLS-1$
  private static final String TAG_NAME = "Name"; //$NON-NLS-1$

  private final TextPersister textPersister = new TextPersister();

  @Override
  public void save(OutputStream stream, ITitledText itemData) throws IOException, PersistenceException {
    Element rootElement = DocumentHelper.createElement("Item"); //$NON-NLS-1$
    save(itemData, rootElement);
    Document document = DocumentHelper.createDocument(rootElement);
    DocumentUtilities.save(document, stream);
  }

  @Override
  public ITitledText load(Document itemXml) throws PersistenceException {
    Element rootElement = itemXml.getRootElement();
    TitledText data = new TitledText();
    load(rootElement, data);
    return data;
  }

  public ITitledText createNew() {
    return new TitledText();
  }

  private void save(ITitledText item, Element rootElement) {
    textPersister.saveText(rootElement, TAG_NAME, item.getName().getText());
    textPersister.saveTextualDescription(rootElement, TAG_SUMMARY, item.getContent());
  }

  private void load(Element parent, ITitledText item) {
    textPersister.restoreTextualDescription(parent, TAG_NAME, item.getName());
    textPersister.restoreTextualDescription(parent, TAG_SUMMARY, item.getContent());
  }
}