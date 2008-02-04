/**
 * 
 */
package net.sf.anathema.character.description;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.resource.CharacterDisplayNameProvider;

import org.eclipse.core.resources.IResource;

public final class DescriptionCharacterDisplayNameProvider implements IDisplayNameProvider {
  private final IResource resource;
  private final ICharacterDescription description;

  public DescriptionCharacterDisplayNameProvider(IResource resource, ICharacterDescription description) {
    this.resource = resource;
    this.description = description;
  }

  @Override
  public String getDisplayName() {
    String characterName = description.getName().getText();
    if (StringUtilities.isNullOrEmpty(characterName)) {
      return new CharacterDisplayNameProvider(resource.getParent()).getDisplayName();
    }
    return characterName;
  }
}