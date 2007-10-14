package net.sf.anathema.character.core.resource;

import java.text.MessageFormat;

import net.sf.anathema.character.core.model.ModelExtensionPoint;
import net.sf.anathema.character.core.repository.internal.ModelDisplayConfiguration;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;

public class ModelResourceNameProvider {

  public String getResourceName(IResource resource) {
    IFolder folder = (IFolder) resource.getParent();
    String characterPrintName = new CharacterPrintNameProvider().getPrintName(folder, folder.getName());
    return getResourceName(resource, characterPrintName);
  }

  public String getResourceName(IResource resource, String characterName) {
    ModelDisplayConfiguration configuration = new ModelExtensionPoint().getDisplayConfiguration(resource);
    return MessageFormat.format(
        Messages.ModelResourceNameProvider_ModelDisplayNamePattern,
        configuration.getDisplayName(),
        characterName);
  }
}