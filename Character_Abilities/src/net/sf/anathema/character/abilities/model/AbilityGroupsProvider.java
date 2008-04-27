package net.sf.anathema.character.abilities.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.trait.group.TraitGroup;

public class AbilityGroupsProvider {

  private static final String ATTRIB_TRAIT_ID = "traitId"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String ATTRIB_LABEL = "label"; //$NON-NLS-1$
  private static final String ATTRIB_CHARACTER_TYPE = "characterType"; //$NON-NLS-1$
  private static final String POINT_ID = "groups"; //$NON-NLS-1$
  private final IPluginExtension[] extensions;

  public AbilityGroupsProvider() {
    this(new EclipseExtensionPoint(IAbilitiesPluginConstants.PLUGIN_ID, POINT_ID).getExtensions());
  }

  public AbilityGroupsProvider(IPluginExtension... extensions) {
    this.extensions = extensions;
  }

  public TraitGroup[] getTraitGroups(String characterType) {
    IExtensionElement groupListElement = getElement(characterType);
    if (groupListElement != null) {
      return createGroups(groupListElement);
    }
    return new DefaultAbilityGroups().get();
  }

  private TraitGroup[] createGroups(IExtensionElement groupListElement) {
    List<TraitGroup> traitGroups = new ArrayList<TraitGroup>();
    for (IExtensionElement groupElement : groupListElement.getElements()) {
      List<String> traitReferences = new ArrayList<String>();
      String groupId = groupElement.getAttribute(ATTRIB_ID);
      for (IExtensionElement referenceElement : groupElement.getElements()) {
        traitReferences.add(referenceElement.getAttribute(ATTRIB_TRAIT_ID));
      }
      String label = groupElement.getAttribute(ATTRIB_LABEL);
      traitGroups.add(new TraitGroup(groupId, label, traitReferences.toArray(new String[traitReferences.size()])));
    }
    return traitGroups.toArray(new TraitGroup[traitGroups.size()]);
  }

  private IExtensionElement getElement(String characterType) {
    for (IPluginExtension extension : extensions) {
      for (IExtensionElement element : extension.getElements()) {
        if (element.getAttribute(ATTRIB_CHARACTER_TYPE).equals(characterType)) {
          return element;
        }
      }
    }
    return null;
  }
}