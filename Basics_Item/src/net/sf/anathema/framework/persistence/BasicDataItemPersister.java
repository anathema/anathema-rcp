package net.sf.anathema.framework.persistence;

import java.io.IOException;
import java.io.OutputStream;

import net.sf.anathema.framework.item.IItem;
import net.sf.anathema.framework.item.data.BasicItemData;
import net.sf.anathema.framework.item.data.BasicsPersister;
import net.sf.anathema.framework.item.data.IBasicItemData;
import net.sf.anathema.framework.repository.AnathemaDataItem;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class BasicDataItemPersister implements ISingleFileItemPersister<IBasicItemData> {

  private final BasicsPersister basicItemDataPersister = new BasicsPersister();
  private final RepositoryItemPersister repositoryItemPerister = new RepositoryItemPersister();

  @Override
  public void save(OutputStream stream, IItem<IBasicItemData> item) throws IOException {
    Element rootElement = DocumentHelper.createElement("Item"); //$NON-NLS-1$
    repositoryItemPerister.save(rootElement, item);
    basicItemDataPersister.save(item.getItemData(), rootElement);
    Document document = DocumentHelper.createDocument(rootElement);
    DocumentUtilities.save(document, stream);
  }

  @Override
  public IItem<IBasicItemData> load(Document itemXml) throws PersistenceException {
    Element rootElement = itemXml.getRootElement();
    BasicItemData data = new BasicItemData();
    AnathemaDataItem<IBasicItemData> item = new AnathemaDataItem<IBasicItemData>(data);
    repositoryItemPerister.load(rootElement, item);
    basicItemDataPersister.load(rootElement, item.getItemData());
    return item;
  }

  @Override
  public IItem<IBasicItemData> createNew() {
    return new AnathemaDataItem<IBasicItemData>(new BasicItemData());
  }
}