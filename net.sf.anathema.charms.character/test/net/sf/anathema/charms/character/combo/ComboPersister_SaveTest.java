package net.sf.anathema.charms.character.combo;

import static net.sf.anathema.test.hamcrest.AnathemaMatchers.*;
import static org.dom4j.DocumentHelper.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import net.sf.anathema.character.core.fake.DummyDocumentFactory;
import net.sf.anathema.charms.character.combo.Combo;
import net.sf.anathema.charms.character.combo.ComboModel;
import net.sf.anathema.charms.character.combo.ComboPersister;
import net.sf.anathema.charms.character.combo.IComboModel;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class ComboPersister_SaveTest {

  private ComboPersister persister;
  private DummyDocumentFactory documentFactory;
  private ComboModel comboModel;
  private Combo combo;

  @Before
  public void createPersister() throws Exception {
    documentFactory = new DummyDocumentFactory();
    documentFactory.document = createDocument(createElement("model"));
    this.persister = new ComboPersister(documentFactory);
  }

  @Before
  public void createModel() throws Exception {
    this.comboModel = new ComboModel();
    this.combo = new Combo();
  }

  @Test
  public void reloadsEmptyModel() throws Exception {
    IComboModel loadedModel = saveAndLoad();
    assertThat(loadedModel.getCreationLearned(), is(empty()));
    assertThat(loadedModel.getExperienceLearned(), is(empty()));
  }

  @Test
  public void reloadsCreationLearnedCombo() throws Exception {
    comboModel.addCreationLearned(combo);
    IComboModel loadedModel = saveAndLoad();
    assertAndReturnsSingleItem(loadedModel.getCreationLearned());
    assertThat(loadedModel.getExperienceLearned(), is(empty()));
  }

  @Test
  public void reloadsExperiencedLearnedCombo() throws Exception {
    comboModel.addExperienceLearned(combo);
    IComboModel loadedModel = saveAndLoad();
    assertAndReturnsSingleItem(loadedModel.getExperienceLearned());
    assertThat(loadedModel.getCreationLearned(), is(empty()));
  }

  @Test
  public void reloadsNameOfCombo() throws Exception {
    comboModel.addCreationLearned(combo);
    combo.name = "Ole Magic";
    IComboModel loadedModel = saveAndLoad();
    Combo loadedCombo = assertAndReturnsSingleItem(loadedModel.getCreationLearned());
    assertThat(loadedCombo.name, is("Ole Magic"));
  }

  @Test
  public void loadsDescriptionOfCombo() throws Exception {
    combo.description = "marvellous wonder";
    comboModel.addCreationLearned(combo);
    IComboModel loadedModel = saveAndLoad();
    Combo loadedCombo = assertAndReturnsSingleItem(loadedModel.getCreationLearned());
    assertThat(loadedCombo.description, is("marvellous wonder"));
  }

  @Test
  public void loadsCharmOfCombo() throws Exception {
    combo.charms.add(new CharmId("{0}", "Wisdom"));
    comboModel.addCreationLearned(combo);
    IComboModel loadedModel = saveAndLoad();
    Combo loadedCombo = assertAndReturnsSingleItem(loadedModel.getCreationLearned());
    assertThat(loadedCombo.charms.size(), is(1));
    assertThat(loadedCombo.charms.get(0).getIdPattern(), is("{0}"));
    assertThat(loadedCombo.charms.get(0).getPrimaryTrait(), is("Wisdom"));
  }

  private IComboModel saveAndLoad() throws IOException, PersistenceException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    persister.save(outputStream, comboModel);
    InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
    return persister.load(DocumentUtilities.read(inputStream), null);
  }

  private Combo assertAndReturnsSingleItem(Iterable<Combo> iterable) {
    Iterator<Combo> iterator = iterable.iterator();
    Combo firstCombo = iterator.next();
    assertThat(iterator.hasNext(), is(false));
    return firstCombo;
  }
}