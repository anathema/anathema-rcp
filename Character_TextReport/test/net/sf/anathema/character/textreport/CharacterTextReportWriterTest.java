package net.sf.anathema.character.textreport;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.textreport.encoder.ITextReportEncoder;

import org.easymock.EasyMock;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Before;
import org.junit.Test;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.MultiColumnText;

public class CharacterTextReportWriterTest {

  private Element lastElement;
  private List<ITextReportEncoder> encoders;
  private ICharacter character;
  private Document document;
  
  @Before
  public void performPrintToCharacterTextReportWriter() throws Exception {
    encoders = new ArrayList<ITextReportEncoder>();
    document = new Document() {
      @Override
      public boolean add(Element element) throws DocumentException {
        lastElement = element;
        return true;
      }
    };
    document.open();
    character = EasyMock.createMock(ICharacter.class);
  }

  private void performPrint(final List<Phrase> addedPhrases) throws DocumentException {
    CharacterTextReportWriter writer = new CharacterTextReportWriter(encoders) {
      @Override
      protected void addPhrase(MultiColumnText columnText, Phrase phrase) throws DocumentException {
        addedPhrases.add(phrase);
      }
    };
    writer.performPrint(new NullProgressMonitor(), character, document, null);
  }
  
  @Test
  public void emptyMultiColumnTextIsAddedToDocumentWithoutEncoders() throws Exception {
    performPrint(new ArrayList<Phrase>());
    assertTrue(lastElement instanceof MultiColumnText);
  }
  
  @Test
  public void encoderPhraseIsAdded() throws Exception {
    Phrase phrase = new Phrase();
    ITextReportEncoder encoder = EasyMock.createMock(ITextReportEncoder.class);
    EasyMock.expect(encoder.createParagraphs(character)).andReturn(phrase);
    EasyMock.replay(encoder);
    encoders.add(encoder);
    ArrayList<Phrase> addedPhrases = new ArrayList<Phrase>();
    performPrint(addedPhrases);
    assertEquals(1, addedPhrases.size());
    assertTrue(addedPhrases.contains(phrase));
  }
}