package net.sf.anathema.backgrounds;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.sf.anathema.character.backgrounds.BackgroundModel;
import net.sf.anathema.character.backgrounds.BackgroundPersister;
import net.sf.anathema.character.backgrounds.IBackgroundModel;
import net.sf.anathema.lib.creation.StaticFactory;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.junit.Before;
import org.junit.Test;

public class BackgroundPersister_Test {

  private BackgroundPersister persister;
  private IBackgroundModel model;

  @Before
  public void createPersister() throws Exception {
    Document document = DocumentHelper.createDocument(DocumentHelper.createElement("root")); //$NON-NLS-1$
    persister = new BackgroundPersister(new StaticFactory<Document, RuntimeException>(document));
  }

  @Before
  public void createModel() throws Exception {
    model = new BackgroundModel();   
  }
  
  @Test
  public void reloadsEmptyModel() throws Exception {
    IBackgroundModel loadedModel = saveAndLoad(model);
    assertThat(loadedModel.getBackgrounds().isEmpty(), is(true));
  }

  @Test
  public void reloadsModelWithOneLearnedCharm() throws Exception {
    model.addBackground("BG"); //$NON-NLS-1$
    IBackgroundModel loadedModel = saveAndLoad(model);
    assertThat(loadedModel.getBackgrounds().size(), is(1));
    assertThat(loadedModel.getBackgrounds().get(0), is("BG")); //$NON-NLS-1$
  }

  private IBackgroundModel saveAndLoad(IBackgroundModel model) throws IOException, PersistenceException {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    persister.save(stream, model);
    byte[] saveData = stream.toByteArray();
    return persister.load(DocumentUtilities.read(new String(saveData)), null);
  }
}