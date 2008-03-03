package charactertype.solar.acceptance;

import static org.junit.Assert.*;
import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.character.core.character.CharacterId;
import net.sf.anathema.character.core.create.CharacterFactory;
import net.sf.anathema.character.core.create.CharacterRepositoryUtilities;
import net.sf.anathema.character.freebies.abilities.util.IAbilityFreebiesConstants;
import net.sf.anathema.character.freebies.configuration.CreditManager;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SolarAbilityFreebiesTest {

  private IFolder folder;
  private CharacterId characterId;
  private CreditManager creditManager;

  @Before
  public void createSolar() {
    String folderName = "Solar"; //$NON-NLS-1$
    new CharacterFactory().createNewCharacter(IIntegrationConstants.DEFAULT_TEMPLATE, folderName);
    IProject project = RepositoryUtilities.getProject(CharacterRepositoryUtilities.getCharacterItemType());
    folder = project.getFolder(folderName);
    characterId = new CharacterId(folder);
  }

  @Before
  public void createCreditManager() throws Exception {
    creditManager = new CreditManager();
  }

  @Test
  public void hasUnlimitedCredit18() throws Exception {
    assertEquals(18, creditManager.getCredit(characterId, IAbilityFreebiesConstants.UNLIMITED_CREDIT));
  }

  @Test
  public void hasFavoredCredit10() throws Exception {
    assertEquals(10, creditManager.getCredit(characterId, IAbilityFreebiesConstants.FAVORED_CREDIT));
  }

  @After
  public void deleteSolarFolder() throws CoreException {
    folder.delete(true, null);
  }
}