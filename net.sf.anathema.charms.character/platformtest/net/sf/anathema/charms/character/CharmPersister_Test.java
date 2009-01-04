package net.sf.anathema.charms.character;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

import net.sf.anathema.charms.character.model.CharmModel;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.junit.Before;
import org.junit.Test;

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
    model.toggleCreationLearned(new CharmId("myCharm", "myTrait")); //$NON-NLS-1$ //$NON-NLS-2$
    ICharmModel loadedModel = saveAndLoad(model);
    Iterator<ICharmId> learnedCharms = loadedModel.getCreationLearnedCharms().iterator();
    ICharmId charmId = learnedCharms.next();
    assertThat(charmId.getId(), is("myCharm")); //$NON-NLS-1$
    assertThat(learnedCharms.hasNext(), is(false));
  }

  private ICharmModel saveAndLoad(ICharmModel model) throws IOException, PersistenceException {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    persister.save(stream, model);
    byte[] saveData = stream.toByteArray();
    return persister.load(DocumentUtilities.read(new String(saveData)), null);
  }
}