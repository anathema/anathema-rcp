package net.sf.anathema.character.trait.preference;

import static org.junit.Assert.*;
import net.sf.anathema.character.trait.plugin.CharacterTraitPlugin;

import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.PlatformUI;
import org.junit.Test;

public class TraitPreferenceConfigurationTest {

  private IPreferenceNode getNode(IPreferenceNode[] nodes, String id) {
    for (IPreferenceNode node : nodes) {
      if (node.getId().equals(id)) {
        return node;
      }
    }
    return null;
  }

  @Test
  public void leaveUnchangedIsConfiguredAsDefaultPreferenceForExperienceTreatment() throws Exception {
    IPreferenceStore preferenceStore = CharacterTraitPlugin.getDefaultInstance().getPreferenceStore();
    String experienceTreatmentDefault = preferenceStore.getDefaultString("trait.experienceTreatment"); //$NON-NLS-1$
    assertSame(ExperienceTraitTreatment.LeaveUnchanged, ExperienceTraitTreatment.valueOf(experienceTreatmentDefault));
  }

  @Test
  public void preferencePageForTraitNodeAsChildOfCharacterNode() throws Exception {
    IPreferenceNode[] rootNodes = PlatformUI.getWorkbench().getPreferenceManager().getRootSubNodes();
    IPreferenceNode characterNode = getNode(rootNodes, "character.preferences"); //$NON-NLS-1$
    IPreferenceNode traitNode = getNode(characterNode.getSubNodes(), "character.traits"); //$NON-NLS-1$
    traitNode.createPage();
    assertNotNull(traitNode.getPage());
  }
}