package net.sf.anathema.character.core.model.content;

import net.sf.anathema.basics.eclipse.extension.AttributePredicate;
import net.sf.anathema.basics.eclipse.extension.ClassConveyerBelt;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;

public class ModelContentChecker implements IContentChecker {

  private static final String ATTRIB_CONTENT_ID = "contentId"; //$NON-NLS-1$

  public String getContentValue(String definition) {
    int seperator = definition.lastIndexOf('.');
    return definition.substring(seperator + 1);
  }

  public IModelContentCheck getCheck(String definition) {
    int seperator = definition.lastIndexOf('.');
    final String contentId = definition.substring(0, seperator);
    EclipseExtensionPoint extensionPoint = new EclipseExtensionPoint(CharacterCorePlugin.ID, "modelcheck"); //$NON-NLS-1$
    AttributePredicate predicate = AttributePredicate.FromNameAndValue(ATTRIB_CONTENT_ID, contentId);
    return new ClassConveyerBelt<IModelContentCheck>(extensionPoint, IModelContentCheck.class, predicate).getFirstObject();
  }
}