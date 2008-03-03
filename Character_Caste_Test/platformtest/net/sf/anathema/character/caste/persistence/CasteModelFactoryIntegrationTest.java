package net.sf.anathema.character.caste.persistence;

import static org.junit.Assert.*;
import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.caste.ICaste;
import net.sf.anathema.character.caste.ICasteModel;
import net.sf.anathema.character.caste.fake.IntegrationCasteModelFactory;
import net.sf.anathema.character.core.character.ICharacterTemplate;

import org.easymock.EasyMock;
import org.junit.Test;

public class CasteModelFactoryIntegrationTest {

  public static final class CasteToPrintNameTransformer implements ITransformer<ICaste, String> {
    @Override
    public String transform(ICaste caste) {
      return caste.getPrintName();
    }
  }

  @Test
  public void createdTemplateReturnsIdsAsOptions() throws Exception {
    // TODO Hier kommt nur der i18n-String zurück. Warum?
    assertCastesAreFoundForCharacterType("test.type", new String[] { "test.name" }, null); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Test
  public void noMortalCastesAreFound() throws Exception {
    assertCastesAreFoundForCharacterType("net.sf.anathema.character.type.mortal", new String[0], null); //$NON-NLS-1$
  }

  @Test
  public void solarCastesAreFound() throws Exception {
    String[] castes = new String[] { "Dawn", "Zenith", "Twilight", "Night", "Eclipse" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
    assertCastesAreFoundForCharacterType("net.sf.anathema.character.type.solar", castes, "net.sf.anathema.character.abilities.model"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Test
  public void lunarCastesAreFound() throws Exception {
    String[] castes = new String[] { "Full Moon", "Changing Moon", "No Moon" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    assertCastesAreFoundForCharacterType("net.sf.anathema.character.type.lunar", castes, "net.sf.anathema.character.attributes.model"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Test
  public void siderealCastesAreFound() throws Exception {
    String[] castes = new String[] { "Journeys", "Serenity", "Battles", "Secrets", "Endings" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
    assertCastesAreFoundForCharacterType("net.sf.anathema.character.type.sidereal", castes, "net.sf.anathema.character.abilities.model"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Test
  public void dragonBloodedAspectsAreFound() throws Exception {
    String[] castes = new String[] { "Air", "Earth", "Fire", "Water", "Wood" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
    assertCastesAreFoundForCharacterType("net.sf.anathema.character.type.db", castes, "net.sf.anathema.character.abilities.model"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  private void assertCastesAreFoundForCharacterType(String characterType, String[] castes, String traitCollectionModelId) {
    ICharacterTemplate template = EasyMock.createMock(ICharacterTemplate.class);
    EasyMock.expect(template.getCharacterTypeId()).andReturn(characterType).anyTimes();
    EasyMock.replay(template);
    ICasteModel casteModel = IntegrationCasteModelFactory.createCasteModel(template);
    assertArrayEquals(castes, ArrayUtilities.transform(
        casteModel.getOptions(),
        String.class,
        new CasteToPrintNameTransformer()));
    assertEquals(traitCollectionModelId, casteModel.getTraitModelId());
  }
}