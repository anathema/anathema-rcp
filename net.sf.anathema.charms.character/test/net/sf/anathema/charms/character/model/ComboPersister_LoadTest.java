package net.sf.anathema.charms.character.model;

import static net.sf.anathema.test.hamcrest.AnathemaMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Iterator;

import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class ComboPersister_LoadTest {

  private ComboPersister persister;

  @Before
  public void createPersister() throws Exception {
    this.persister = new ComboPersister(null);
  }

  @Test
  public void loadsEmptyDocument() throws Exception {
    String emptyXml = "<model/>";
    IComboModel loadedModel = loadFromXml(emptyXml);
    assertThat(loadedModel.getCreationLearned(), is(empty()));
    assertThat(loadedModel.getExperienceLearned(), is(empty()));
  }

  @Test
  public void loadsCreationLearnedCombo() throws Exception {
    String xml = "<model><combo experienced=\"false\"/></model>";
    IComboModel loadedModel = loadFromXml(xml);
    assertAndReturnsSingleItem(loadedModel.getCreationLearned());
    assertThat(loadedModel.getExperienceLearned(), is(empty()));
  }

  @Test
  public void loadsExperiencedLearnedCombo() throws Exception {
    String xml = "<model><combo experienced=\"true\"/>s</model>";
    IComboModel loadedModel = loadFromXml(xml);
    assertAndReturnsSingleItem(loadedModel.getExperienceLearned());
    assertThat(loadedModel.getCreationLearned(), is(empty()));
  }

  @Test
  public void loadsNameOfCombo() throws Exception {
    String xml = "<model><combo experienced=\"false\"><name>ferocious smash</name></combo></model>";
    IComboModel loadedModel = loadFromXml(xml);
    Combo combo = assertAndReturnsSingleItem(loadedModel.getCreationLearned());
    assertThat(combo.name, is("ferocious smash"));
  }

  @Test
  public void loadsDescriptionOfCombo() throws Exception {
    String xml = "<model><combo experienced=\"false\"><description>marvellous wonder</description></combo></model>";
    IComboModel loadedModel = loadFromXml(xml);
    Combo combo = assertAndReturnsSingleItem(loadedModel.getCreationLearned());
    assertThat(combo.description, is("marvellous wonder"));
  }

  @Test
  public void loadsCharmOfCombo() throws Exception {
    String xml = "<model><combo experienced=\"false\">"
        + "<charm><id>shamu {0} desu</id><trait>hops</trait></charm>"
        + "</combo></model>";
    IComboModel loadedModel = loadFromXml(xml);
    Combo combo = assertAndReturnsSingleItem(loadedModel.getCreationLearned());
    assertThat(combo.charms.size(), is(1));
    assertThat(combo.charms.get(0).getIdPattern(), is("shamu {0} desu"));
    assertThat(combo.charms.get(0).getPrimaryTrait(), is("hops"));
  }

  private IComboModel loadFromXml(String emptyXml) throws PersistenceException {
    Document document = DocumentUtilities.read(emptyXml);
    return persister.load(document, null);
  }

  private Combo assertAndReturnsSingleItem(Iterable<Combo> iterable) {
    Iterator<Combo> iterator = iterable.iterator();
    Combo combo = iterator.next();
    assertThat(iterator.hasNext(), is(false));
    return combo;
  }
}