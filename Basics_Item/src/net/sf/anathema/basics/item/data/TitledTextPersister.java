package net.sf.anathema.basics.item.data;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.item.persistence.IItemPersister;
import net.sf.anathema.basics.item.persistence.ISingleFileItemPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class TitledTextPersister implements ISingleFileItemPersister<ITitledText> {

  private final BasicsPersister basicItemDataPersister = new BasicsPersister();
  private final IExtensionProvider provider = new EclipseExtensionProvider();

  @Override
  public void save(OutputStream stream, ITitledText itemData) throws IOException, PersistenceException {
    Element rootElement = DocumentHelper.createElement("Item"); //$NON-NLS-1$
    basicItemDataPersister.save(itemData, rootElement);
    for (IItemPersister persister : getRegisteredPersisters()) {
      persister.save(rootElement, itemData);
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
  public ITitledText load(Document itemXml) throws PersistenceException {
    Element rootElement = itemXml.getRootElement();
    TitledText data = new TitledText();
    basicItemDataPersister.load(rootElement, data);
    for (IItemPersister persister : getRegisteredPersisters()) {
      persister.load(rootElement, data);
    }
    return data;
  }

  @Override
  public ITitledText createNew() {
    return new TitledText();
  }
}