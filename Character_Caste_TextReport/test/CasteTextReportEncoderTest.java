import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.util.Iterator;

import net.sf.anathema.character.caste.fake.DummyCaste;
import net.sf.anathema.character.caste.model.CasteModel;
import net.sf.anathema.character.caste.model.CasteTemplate;
import net.sf.anathema.character.caste.model.ICasteModel;
import net.sf.anathema.character.caste.textreport.CasteTextReportEncoder;
import net.sf.anathema.character.core.character.ICharacter;

import org.junit.Test;

import com.lowagie.text.Chunk;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;

public class CasteTextReportEncoderTest {

  @Test
  public void createsNoParagraphsForModelWithoutCasteModel() throws Exception {
    ICharacter character = createCharacterWithCasteModel(null);
    Iterable<Element> paragraphs = new CasteTextReportEncoder().createParagraphs(character);
    assertFalse(paragraphs.iterator().hasNext());
  }

  @Test
  public void createsNoParagraphsForModelWithoutSetCaste() throws Exception {
    ICasteModel casteModel = new CasteModel(new CasteTemplate(new DummyCaste("myCaste"))); //$NON-NLS-1$
    ICharacter character = createCharacterWithCasteModel(casteModel);
    Iterable<Element> paragraphs = new CasteTextReportEncoder().createParagraphs(character);
    assertFalse(paragraphs.iterator().hasNext());
  }
  
  @Test
  public void createsPhraseForPresentCaste() throws Exception {
    ICharacter character = createCharacterWithCasteModel(createFilledCasteModel());
    Iterator<Element> paragraphs = new CasteTextReportEncoder().createParagraphs(character).iterator();
    assertTrue(paragraphs.next() instanceof Phrase);
    assertFalse(paragraphs.hasNext());
  }
  
  @Test
  public void castePhraseStartsWithCasteLabel() throws Exception {
    ICharacter character = createCharacterWithCasteModel(createFilledCasteModel());
    Iterator<Element> paragraphs = new CasteTextReportEncoder().createParagraphs(character).iterator();
    Phrase phrase = (Phrase) paragraphs.next();
    assertEquals("Caste:", ((Chunk) phrase.getChunks().get(0)).getContent()); //$NON-NLS-1$
  }
  
  @Test
  public void castePhraseEndsWithCastePrintName() throws Exception {
    ICharacter character = createCharacterWithCasteModel(createFilledCasteModel());
    Iterator<Element> paragraphs = new CasteTextReportEncoder().createParagraphs(character).iterator();
    Phrase phrase = (Phrase) paragraphs.next();
    assertEquals("myCastePrintName", ((Chunk) phrase.getChunks().get(1)).getContent()); //$NON-NLS-1$
  }

  private ICasteModel createFilledCasteModel() {
    DummyCaste caste = new DummyCaste("myCaste"); //$NON-NLS-1$
    ICasteModel casteModel = new CasteModel(new CasteTemplate(caste));
    casteModel.setCasteByPrintName(caste.getPrintName());
    return casteModel;
  }

  private ICharacter createCharacterWithCasteModel(ICasteModel casteModel) {
    ICharacter character = createMock(ICharacter.class);
    expect(character.getModel(ICasteModel.ID)).andReturn(casteModel);
    replay(character);
    return character;
  }
}