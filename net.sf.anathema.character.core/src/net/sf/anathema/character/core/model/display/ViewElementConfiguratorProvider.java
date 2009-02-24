package net.sf.anathema.character.core.model.display;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.ClassConveyerBelt;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.character.core.model.IViewElementConfigurator;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;

public class ViewElementConfiguratorProvider {

  private static final String EXTENSIN_POINT = "viewelementconfigurator"; //$NON-NLS-1$

  public List<IViewElementConfigurator> getFor(final String modelId) {
    ModelIdPredicate predicate = new ModelIdPredicate(modelId);
    EclipseExtensionPoint extensionPoint = new EclipseExtensionPoint(CharacterCorePlugin.ID, EXTENSIN_POINT);
    ClassConveyerBelt<IViewElementConfigurator> classConveyerBelt = new ClassConveyerBelt<IViewElementConfigurator>(
        extensionPoint,
        IViewElementConfigurator.class,
        predicate);
    return classConveyerBelt.getAllObjects();
  }
}
