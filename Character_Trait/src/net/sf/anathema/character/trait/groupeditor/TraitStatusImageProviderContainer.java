package net.sf.anathema.character.trait.groupeditor;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.ClassConveyerBelt;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.character.trait.plugin.CharacterTraitPlugin;
import net.sf.anathema.character.trait.status.ITraitStatusImageProvider;

public class TraitStatusImageProviderContainer {

  private static final String POINT_ID = "traitstatus"; //$NON-NLS-1$

  public List<ITraitStatusImageProvider> getImageProvider() {
    EclipseExtensionPoint extensionPoint = new EclipseExtensionPoint(CharacterTraitPlugin.PLUGIN_ID, POINT_ID);
    Class<ITraitStatusImageProvider> objectClass = ITraitStatusImageProvider.class;
    return new ClassConveyerBelt<ITraitStatusImageProvider>(extensionPoint, objectClass).getAllObjects();
  }
}