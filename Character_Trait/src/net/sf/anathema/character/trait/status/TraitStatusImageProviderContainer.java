package net.sf.anathema.character.trait.status;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.ClassConveyerBelt;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.character.trait.groupeditor.ITraitStatusImageProvider;
import net.sf.anathema.character.trait.plugin.CharacterTraitPlugin;

public class TraitStatusImageProviderContainer {

  public List<ITraitStatusImageProvider> getImageProvider() {
    EclipseExtensionPoint extensionPoint = new EclipseExtensionPoint(CharacterTraitPlugin.PLUGIN_ID, "traitStatus"); //$NON-NLS-1$
    Class<ITraitStatusImageProvider> objectClass = ITraitStatusImageProvider.class;
    ClassConveyerBelt<ITraitStatusImageProvider> belt = new ClassConveyerBelt<ITraitStatusImageProvider>(
        extensionPoint,
        objectClass);
    List<ITraitStatusImageProvider> allProviders = belt.getAllObjects();
    return allProviders;
  }
}