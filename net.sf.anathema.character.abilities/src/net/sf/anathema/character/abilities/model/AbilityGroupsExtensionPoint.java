package net.sf.anathema.character.abilities.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.AttributePredicate;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionPoint;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.trait.group.TraitGroup;

public class AbilityGroupsExtensionPoint {

  private static final String ATTRIB_TRAIT_ID = "traitId"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String ATTRIB_LABEL = "label"; //$NON-NLS-1$
  private static final String ATTRIB_CHARACTER_TYPE = "characterType"; //$NON-NLS-1$
  private static final String POINT_ID = "groups"; //$NON-NLS-1$
  private final IExtensionPoint extensions;

  public AbilityGroupsExtensionPoint() {
    this(new EclipseExtensionPoint(IAbilitiesPluginConstants.PLUGIN_ID, POINT_ID));
  }

  public AbilityGroupsExtensionPoint(IExtensionPoint extensions) {
    this.extensions = extensions;
  }

  public TraitGroup[] getTraitGroups(String characterType) {
    IExtensionElement groupListElement = getElementFor(characterType);
    if (groupListElement != null) {
      return readAllGroups(groupListElement);
    }
    return new DefaultAbilityGroups().get();
  }

  private IExtensionElement getElementFor(final String characterType) {
     return extensions.getFirst(AttributePredicate.FromNameAndValue(ATTRIB_CHARACTER_TYPE, characterType));
  }

  private TraitGroup[] readAllGroups(IExtensionElement groupListElement) {
    List<TraitGroup> traitGroups = new ArrayList<TraitGroup>();
    for (IExtensionElement groupElement : groupListElement.getElements()) {
      traitGroups.add(readGroup(groupElement));
    }
    return traitGroups.toArray(new TraitGroup[traitGroups.size()]);
  }

  private TraitGroup readGroup(IExtensionElement groupElement) {
    String[] traitReferences = readTraitReferences(groupElement);
    String groupId = groupElement.getAttribute(ATTRIB_ID);
    String label = groupElement.getAttribute(ATTRIB_LABEL);
    return new TraitGroup(groupId, label, traitReferences);
  }

  private String[] readTraitReferences(IExtensionElement groupElement) {
    List<String> traitReferences = new ArrayList<String>();
    for (IExtensionElement referenceElement : groupElement.getElements()) {
      traitReferences.add(referenceElement.getAttribute(ATTRIB_TRAIT_ID));
    }
    return traitReferences.toArray(new String[traitReferences.size()]);
  }
}