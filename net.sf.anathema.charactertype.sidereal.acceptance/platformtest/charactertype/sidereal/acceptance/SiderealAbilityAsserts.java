package charactertype.sidereal.acceptance;

import static org.junit.Assert.*;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.acceptance.AcceptanceTraitUtilities;
import net.sf.anathema.character.acceptance.TraitInteractionUtilties;
import net.sf.anathema.character.core.character.CharacterId;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;

public class SiderealAbilityAsserts {

  private static void assertHasTraitValueOf(IFolder folder, String traitId, int expectedValue) {
    ModelIdentifier abilityIdentifier = new ModelIdentifier(new CharacterId(folder), IAbilitiesPluginConstants.MODEL_ID);
    ITraitCollectionModel abilities = (ITraitCollectionModel) ModelCache.getInstance().getModel(abilityIdentifier);
    assertEquals(expectedValue, abilities.getTrait(traitId).getCreationModel().getValue());
  }

  public static void assertCastelessMinimalAbilities(IFolder folder) {
    assertHasTraitValueOf(folder, "Archery", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Athletics", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Awareness", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Bureaucracy", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Craft", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Dodge", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Integrity", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Investigation", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Larceny", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Linguistics", 1); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Lore", 3); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "MartialArts", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Medicine", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Melee", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Occult", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Performance", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Presence", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Resistance", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Ride", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Sail", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Socialize", 1); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Stealth", 1); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Survival", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Thrown", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "War", 0); //$NON-NLS-1$
  }

  public static void assertJourneysMinimalAbilities(IFolder folder) {
    assertHasTraitValueOf(folder, "Archery", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Athletics", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Awareness", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Bureaucracy", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Craft", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Dodge", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Integrity", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Investigation", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Larceny", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Linguistics", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Lore", 3); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "MartialArts", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Medicine", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Melee", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Occult", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Performance", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Presence", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Resistance", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Ride", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Sail", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Socialize", 1); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Stealth", 1); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Survival", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Thrown", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "War", 0); //$NON-NLS-1$
  }

  public static void assertSerenityMinimalAbilities(IFolder folder) {
    assertHasTraitValueOf(folder, "Archery", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Athletics", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Awareness", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Bureaucracy", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Craft", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Dodge", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Integrity", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Investigation", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Larceny", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Linguistics", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Lore", 3); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "MartialArts", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Medicine", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Melee", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Occult", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Performance", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Presence", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Resistance", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Ride", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Sail", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Socialize", 3); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Stealth", 1); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Survival", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Thrown", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "War", 0); //$NON-NLS-1$
  }

  public static void assertBattlesMinimalAbilities(IFolder folder) {
    assertHasTraitValueOf(folder, "Archery", 3); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Athletics", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Awareness", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Bureaucracy", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Craft", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Dodge", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Integrity", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Investigation", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Larceny", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Linguistics", 1); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Lore", 3); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "MartialArts", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Medicine", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Melee", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Occult", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Performance", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Presence", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Resistance", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Ride", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Sail", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Socialize", 1); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Stealth", 1); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Survival", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Thrown", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "War", 2); //$NON-NLS-1$
  }

  public static void assertSecretsMinimalAbilities(IFolder folder) {
    assertHasTraitValueOf(folder, "Archery", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Athletics", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Awareness", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Bureaucracy", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Craft", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Dodge", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Integrity", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Investigation", 3); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Larceny", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Linguistics", 1); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Lore", 3); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "MartialArts", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Medicine", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Melee", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Occult", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Performance", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Presence", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Resistance", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Ride", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Sail", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Socialize", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Stealth", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Survival", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Thrown", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "War", 0); //$NON-NLS-1$
  }

  public static void assertEndingsMinimalAbilities(IFolder folder) {
    assertHasTraitValueOf(folder, "Archery", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Athletics", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Awareness", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Bureaucracy", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Craft", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Dodge", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Integrity", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Investigation", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Larceny", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Linguistics", 1); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Lore", 3); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "MartialArts", 3); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Medicine", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Melee", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Occult", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Performance", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Presence", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Resistance", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Ride", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Sail", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Socialize", 1); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Stealth", 2); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Survival", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "Thrown", 0); //$NON-NLS-1$
    assertHasTraitValueOf(folder, "War", 0); //$NON-NLS-1$
  }

  public static void assertAlternateMinimum(IFolder folder, String defaultMinimum, String alternateMinimum, int value)
      throws PersistenceException,
      CoreException,
      ExtensionException {
    List<IDisplayTraitGroup<IInteractiveTrait>> abilities = TraitInteractionUtilties.createDisplayGroups(
        folder,
        IAbilitiesPluginConstants.MODEL_ID);
    IInteractiveTrait defaultTrait = AcceptanceTraitUtilities.extract(abilities, defaultMinimum);
    IInteractiveTrait alternateTrait = AcceptanceTraitUtilities.extract(abilities, alternateMinimum);
    alternateTrait.setValue(value);
    defaultTrait.setValue(0);
    assertEquals(0, defaultTrait.getValue());
    alternateTrait.setValue(0);
    assertEquals(value, alternateTrait.getValue());
  }
}