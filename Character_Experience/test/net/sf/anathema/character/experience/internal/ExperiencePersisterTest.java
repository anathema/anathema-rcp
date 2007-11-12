package net.sf.anathema.character.experience.internal;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.basics.item.persistence.IBundleVersionCollection;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.easymock.EasyMock;
import org.junit.Test;

public class ExperiencePersisterTest {

  private static final String VERSION = "1.0.0"; //$NON-NLS-1$

  @Test
  public void createsVersionedExperienceDocument() throws Exception {
    IBundleVersionCollection collection = EasyMock.createMock(IBundleVersionCollection.class);
    EasyMock.expect(collection.getBundleVersion("net.sf.anathema.character.experience")).andReturn(VERSION); //$NON-NLS-1$
    EasyMock.replay(collection);
    Element root = DocumentHelper.createElement("model"); //$NON-NLS-1$
    root.addAttribute("bundleVersion", VERSION); //$NON-NLS-1$
    root.addAttribute("experienced", "true"); //$NON-NLS-1$ //$NON-NLS-2$
    Document document = DocumentHelper.createDocument(root);
    ExperiencePersister experiencePersister = new ExperiencePersister(collection);
    Experience xp = new Experience();
    xp.setExperienced(true);
    assertEquals(document.asXML(), experiencePersister.createExperienceDocument(xp).asXML());
  }
}