package net.sf.anathema.framework.persistence;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
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
  private final IExtensionProvider provider = new EclipseExtensionProvider();

  @Override
  public void save(OutputStream stream, IItem<IBasicItemData> item) throws IOException, PersistenceException {
    Element rootElement = DocumentHelper.createElement("Item"); //$NON-NLS-1$
    basicItemDataPersister.save(item.getItemData(), rootElement);
    for (IItemPersister persister : getRegisteredPersisters()) {
      persister.save(rootElement, item);
    }
    Document document = DocumentHelper.createDocument(rootElement);
    DocumentUtilities.save(document, stream);
  }

  private Iterable<IItemPersister> getRegisteredPersisters() throws PersistenceException {
    List<IItemPersister> persisters = new ArrayList<IItemPersister>();
    for (IPluginExtension extension : provider.getExtensions("net.sf.anathema.item.persister")) { //$NON-NLS-1$
      for (IExtensionElement element : extension.getElements()) {
        try {
          persisters.add(element.getAttributeAsObject("class", IItemPersister.class)); //$NON-NLS-1$
        }
        catch (ExtensionException e) {
          throw new PersistenceException(e);
        }
      }
    }
    return persisters;
  }

  @Override
  public IItem<IBasicItemData> load(Document itemXml) throws PersistenceException {
    Element rootElement = itemXml.getRootElement();
    BasicItemData data = new BasicItemData();
    AnathemaDataItem<IBasicItemData> item = new AnathemaDataItem<IBasicItemData>(data);
    basicItemDataPersister.load(rootElement, item.getItemData());
    for (IItemPersister persister : getRegisteredPersisters()) {
      persister.load(rootElement, item);
    }
    return item;
  }

  @Override
  public IItem<IBasicItemData> createNew() {
    return new AnathemaDataItem<IBasicItemData>(new BasicItemData());
  }
}