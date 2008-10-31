package net.sf.anathema.character.textreport;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.MultiColumnText;

public class CharaterTextReportWriter_AddPhraseTest {

  
  @Test
  public void phrasesAreAddedToMultiColumnText() throws Exception {
    Phrase phrase = new Phrase();
    CharacterTextReportWriter writer = new CharacterTextReportWriter(null);
    final List<Element> addedElements = new ArrayList<Element>();
    MultiColumnText multiColumnText = new MultiColumnText() {
      @Override
      public void addElement(Element element) throws DocumentException {
        addedElements.add(element);
      }
    };
    writer.addPhrase(multiColumnText, phrase);
    assertEquals(1, addedElements.size());
    assertTrue(addedElements.contains(phrase));
  }

}