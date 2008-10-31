package net.sf.anathema.character.caste.sheet;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.caste.ICaste;
import net.sf.anathema.character.caste.ICasteModel;
import net.sf.anathema.character.caste.fake.DummyCaste;
import net.sf.anathema.character.core.character.ICharacter;

import org.junit.Before;
import org.junit.Test;

public class CasteCharacterTextTest {

  private CasteCharacterText casteText;

  @Before
  public void createText() {
    casteText = new CasteCharacterText();
  }

  @Test
  public void isActiveForCharacterWithCaste() throws Exception {
    assertTrue(casteText.isActiveFor(createCharacter(createMock(ICasteModel.class))));
  }

  @Test
  public void isNotActiveForCharacterWithoutCaste() throws Exception {
    assertFalse(casteText.isActiveFor(createCharacter(null)));
  }

  @Test
  public void returnsCasteLabel() throws Exception {
    assertEquals("Caste:", casteText.getLabel()); //$NON-NLS-1$
  }

  @Test
  public void returnsCastePrintNameForSetCaste() throws Exception {
    ICasteModel casteModel = createCasteModel(new DummyCaste("tumid")); //$NON-NLS-1$
    assertEquals("tumidPrintName", casteText.getTextFor(createCharacter(casteModel))); //$NON-NLS-1$
  }

  @Test
  public void returnsNullForNoChoosenCaste() throws Exception {
    assertNull(casteText.getTextFor(createCharacter(createCasteModel(null)))); 
  }

  public static ICasteModel createCasteModel(ICaste caste) {
    ICasteModel casteModel = createMock(ICasteModel.class);
    expect(casteModel.getCaste()).andReturn(caste);
    replay(casteModel);
    return casteModel;
  }

  public static ICharacter createCharacter(ICasteModel casteModel) {
    ICharacter character = createMock(ICharacter.class);
    expect(character.getModel(ICasteModel.ID)).andReturn(casteModel).anyTimes();
    replay(character);
    return character;
  }
}