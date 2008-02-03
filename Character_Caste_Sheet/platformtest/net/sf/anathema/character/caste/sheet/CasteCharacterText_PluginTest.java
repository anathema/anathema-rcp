package net.sf.anathema.character.caste.sheet;

import static org.junit.Assert.*;
import net.sf.anathema.character.report.text.CharacterTextContainer;
import net.sf.anathema.character.report.text.ICharacterText;

import org.junit.Test;

public class CasteCharacterText_PluginTest {

  @Test
  public void isRegisteredWithIdCaste() throws Exception {
    ICharacterText text = new CharacterTextContainer().getText("caste"); //$NON-NLS-1$
    assertTrue(text instanceof CasteCharacterText);
  }
}