package net.sf.anathema.character.core.model.display;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.ClassConveyerBelt;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.character.core.model.IDisplayAddition;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;

public class DisplayAdditionProvider {

  public List<IDisplayAddition> getFor(final String modelId) {
    return new ClassConveyerBelt<IDisplayAddition>(
        new EclipseExtensionPoint(CharacterCorePlugin.ID, "displayaddition"),
        IDisplayAddition.class,
        new ModelIdPredicate(modelId)).getAllObjects();
  }
}
