package net.sf.anathema.character.caste.persistence;

import static org.junit.Assert.assertArrayEquals;
import net.sf.anathema.character.caste.ICasteModel;
import net.sf.anathema.character.caste.fake.IntegrationCasteModelFactory;
import net.sf.anathema.character.core.character.ICharacterTemplate;

import org.easymock.EasyMock;
import org.junit.Test;

public class CasteModelFactoryIntegrationTest {

  @Test
  public void createdTemplateReturnsIdsAsOptions() throws Exception {
    // TODO Hier kommt nur der i18n-String zurück. Warum?
    assertCastesAreFoundForCharacterType("test.type", new String[] { "test.name" }); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Test
  public void noMortalCastesAreFound() throws Exception {
    assertCastesAreFoundForCharacterType("net.sf.anathema.character.type.mortal", new String[0]); //$NON-NLS-1$
  }

  @Test
  public void solarCastesAreFound() throws Exception {
    String[] castes = new String[] { "Dawn", "Zenith", "Twilight", "Night", "Eclipse" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
    assertCastesAreFoundForCharacterType("net.sf.anathema.character.type.solar", castes); //$NON-NLS-1$
  }

  @Test
  public void lunarCastesAreFound() throws Exception {
    String[] castes = new String[] { "Full Moon", "Changing Moon", "No Moon" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
    assertCastesAreFoundForCharacterType("net.sf.anathema.character.type.lunar", castes); //$NON-NLS-1$
  }

  @Test
  public void siderealCastesAreFound() throws Exception {
    String[] castes = new String[] { "Journeys", "Serenity", "Battles", "Secrets", "Endings" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
    assertCastesAreFoundForCharacterType("net.sf.anathema.character.type.sidereal", castes); //$NON-NLS-1$
  }

  @Test
  public void dragonBloodedAspectsAreFound() throws Exception {
    String[] castes = new String[] { "Air", "Earth", "Fire", "Water", "Wood" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
    assertCastesAreFoundForCharacterType("net.sf.anathema.character.type.db", castes); //$NON-NLS-1$
  }

  private void assertCastesAreFoundForCharacterType(String characterType, String[] castes) {
    ICharacterTemplate template = EasyMock.createMock(ICharacterTemplate.class);
    EasyMock.expect(template.getCharacterTypeId()).andReturn(characterType).anyTimes();
    EasyMock.replay(template);
    ICasteModel casteModel = IntegrationCasteModelFactory.createCasteModel(template);
    assertArrayEquals(castes, casteModel.getOptions());
  }
}