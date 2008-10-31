package net.sf.anathema.character.core.model.display;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.ClassConveyerBelt;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.character.core.model.IViewElementConfigurator;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;

public class ViewElementConfiguratorProvider {

  public List<IViewElementConfigurator> getFor(final String modelId) {
    return new ClassConveyerBelt<IViewElementConfigurator>(
        new EclipseExtensionPoint(CharacterCorePlugin.ID, "viewelementconfigurator"),
        IViewElementConfigurator.class,
        new ModelIdPredicate(modelId)).getAllObjects();
  }
}
