package net.sf.anathema.charms.character;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;

public class CharmPersister_Test {

  private CharmsPersister persister;

  @Before
  public void createPersister() throws Exception {
    Document document = DocumentHelper.createDocument(DocumentHelper.createElement("root")); //$NON-NLS-1$
    persister = new CharmsPersister(new StaticFactory<Document, RuntimeException>(document));
  }

  @Test
  public void reloadsEmptyModel() throws Exception {
    ICharmModel model = new CharmModel();
    ICharmModel loadedModel = saveAndLoad(model);
    assertThat(loadedModel.getCreationLearnedCharms().iterator().hasNext(), is(false));
  }

  @Test
  public void reloadsModelWithOneLearnedCharm() throws Exception {
    ICharmModel model = new CharmModel();
    model.toggleCreationLearned("myCharm"); //$NON-NLS-1$
    ICharmModel loadedModel = saveAndLoad(model);
    assertThat(loadedModel.getCreationLearnedCharms(), JUnitMatchers.hasItem("myCharm")); //$NON-NLS-1$
  }

  private ICharmModel saveAndLoad(ICharmModel model) throws IOException, PersistenceException {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    persister.save(stream, model);
    byte[] saveData = stream.toByteArray();
    return persister.load(DocumentUtilities.read(new String(saveData)), null);
  }
}