package net.sf.anathema.charms;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.zest.core.viewers.IGraphContentProvider;

public class CharmContentProvider implements IGraphContentProvider {

  private static final String ANY_EXCELLENCY = "Any\nExcellency";
  private static final String IRON_CATTLE_BODY = "iron\ncattle\nbody";
  private static final String BATTLE_FURY_FOCUS = "battle\nfury focus";
  private static final String WILLPOWER_ENHANCING_SPIRIT = "willpower\nenhancing\nspirit";
  private static final String ADAMENT_SKIN_TECHNIQUE = "Adament Skin\nTechnique";
  private static final String GLORIOUS_SOLAR_PLATE = "Glorious\nSolar Plate";
  private static final String UNBREAKABLE_WARRIORS_MASTERY = "Unbreakable\nWarriors\nMastery";
  private static final String BLOODTHIRSTY_SWORDDANCER_SPIRIT = "Bloodthirsty\nSworddancer Spirit";
  private static final String BODY_MENDING_MEDITATION = "Body Mending\nMeditation";
  private static final String OX_BODY_TECHNIQUE = "Ox Body\nTechnique";
  private static final String IMMUNITY_TO_EVERYTHING_TECHNIQUE = "Immunity to\nEverything\nTechnique";
  private static final String ESSENCE_GATHERING_TEMPER = "essence gathering\ntemper";
  private static final String SPIRIT_STRENGTHENS_THE_SKIN = "spirit strengthens\nthe skin";
  private static final String WIRLWIND_ARMOR_DONNING_PRANA = "wirlwind armor\ndonning prana";
  private static final String ARMORED_SCOUT_ENVIGORATION = "armored scout\nenvigoration";
  private static final String HAUBERT_LIGHTNING_GESTURE = "hauberk\nlightning gesture";
  private static final String DURABLITY_OF_OAK_MEDITATION = "durablity of\noak meditation";
  private static final String IRON_SKIN_CONCENTRATION = "iron skin\nconcentration";

  public Object getSource(Object rel) {
    return ((CharmPrerequisite) rel).getSource();
  }

  public Object[] getElements(Object input) {
    return new Object[] {
        new CharmPrerequisite(null, "First\nExcellency"),
        new CharmPrerequisite(null, "Second\nExcellency"),
        new CharmPrerequisite(null, "Third\nExcellency"),
        new CharmPrerequisite(null, ANY_EXCELLENCY),
        new CharmPrerequisite(ANY_EXCELLENCY, "mastery"),
        new CharmPrerequisite(ANY_EXCELLENCY, "essence flow"),
        new CharmPrerequisite(ANY_EXCELLENCY, BATTLE_FURY_FOCUS),
        new CharmPrerequisite(ANY_EXCELLENCY, IMMUNITY_TO_EVERYTHING_TECHNIQUE),
        new CharmPrerequisite(ANY_EXCELLENCY, UNBREAKABLE_WARRIORS_MASTERY),
        new CharmPrerequisite(BATTLE_FURY_FOCUS, BLOODTHIRSTY_SWORDDANCER_SPIRIT),
        new CharmPrerequisite(null, BODY_MENDING_MEDITATION),
        new CharmPrerequisite(null, OX_BODY_TECHNIQUE),
        new CharmPrerequisite(null, WIRLWIND_ARMOR_DONNING_PRANA),
        new CharmPrerequisite(null, ESSENCE_GATHERING_TEMPER),
        new CharmPrerequisite(null, DURABLITY_OF_OAK_MEDITATION),
        new CharmPrerequisite(OX_BODY_TECHNIQUE, UNBREAKABLE_WARRIORS_MASTERY),
        new CharmPrerequisite(WIRLWIND_ARMOR_DONNING_PRANA, HAUBERT_LIGHTNING_GESTURE),
        new CharmPrerequisite(HAUBERT_LIGHTNING_GESTURE, GLORIOUS_SOLAR_PLATE),
        new CharmPrerequisite(HAUBERT_LIGHTNING_GESTURE, ARMORED_SCOUT_ENVIGORATION),
        new CharmPrerequisite(ESSENCE_GATHERING_TEMPER, WILLPOWER_ENHANCING_SPIRIT),
        new CharmPrerequisite(DURABLITY_OF_OAK_MEDITATION, SPIRIT_STRENGTHENS_THE_SKIN),
        new CharmPrerequisite(DURABLITY_OF_OAK_MEDITATION, IRON_SKIN_CONCENTRATION),
        new CharmPrerequisite(IRON_SKIN_CONCENTRATION, IRON_CATTLE_BODY),
        new CharmPrerequisite(IRON_SKIN_CONCENTRATION, ADAMENT_SKIN_TECHNIQUE),
        new CharmPrerequisite(SPIRIT_STRENGTHENS_THE_SKIN, ADAMENT_SKIN_TECHNIQUE),
        };
  }

  public Object getDestination(Object rel) {
    return ((CharmPrerequisite) rel).getDestination();
  }

  public void dispose() {
    // nothing to do
  }

  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    // nothing to do
  }
}