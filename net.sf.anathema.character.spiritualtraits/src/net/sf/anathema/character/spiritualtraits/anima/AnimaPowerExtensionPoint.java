package net.sf.anathema.character.spiritualtraits.anima;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;

public class AnimaPowerExtensionPoint {

  private static final String POINT_ID = "animapowers"; //$NON-NLS-1$

  private final EclipseExtensionPoint extensionPoint;

  public AnimaPowerExtensionPoint() {
    this.extensionPoint = new EclipseExtensionPoint(IPluginConstants.PLUGIN_ID, POINT_ID);
  }

  public String[] getPowers(final ICharacter character) {
    ReadAnimaPowerClosure closure = new ReadAnimaPowerClosure(character);
    extensionPoint.forAllDo(closure);
    return closure.getPowers();
  }
}