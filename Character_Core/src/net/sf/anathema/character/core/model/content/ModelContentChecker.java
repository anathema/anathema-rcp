package net.sf.anathema.character.core.model.content;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.ClassConveyerBelt;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;

public class ModelContentChecker implements IContentChecker {

  public static final class ContentIdPredicate implements IPredicate<IExtensionElement> {
    private final String contentId;

    public ContentIdPredicate(String contentId) {
      this.contentId = contentId;
    }

    @Override
    public boolean evaluate(IExtensionElement element) {
      return element.getAttribute("contentId").equals(contentId); //$NON-NLS-1$
    }
  }

  public String getContentValue(String definition) {
    int seperator = definition.lastIndexOf('.');
    return definition.substring(seperator + 1);
  }

  public IModelContentCheck getCheck(String definition) {
    int seperator = definition.lastIndexOf('.');
    final String contentId = definition.substring(0, seperator);
    EclipseExtensionPoint extensionPoint = new EclipseExtensionPoint(CharacterCorePlugin.ID, "modelcheck"); //$NON-NLS-1$
    ContentIdPredicate predicate = new ContentIdPredicate(contentId);
    return new ClassConveyerBelt<IModelContentCheck>(extensionPoint, IModelContentCheck.class, predicate).getFirstObject();
  }
}