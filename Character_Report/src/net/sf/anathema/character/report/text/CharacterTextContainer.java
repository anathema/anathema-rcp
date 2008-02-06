package net.sf.anathema.character.report.text;

import net.sf.anathema.basics.eclipse.extension.ClassConveyerBelt;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.report.plugin.IReportPluginConstants;

public class CharacterTextContainer {

  private final IPluginExtension[] extensions;

  public CharacterTextContainer() {
    this(new EclipseExtensionPoint(IReportPluginConstants.PLUGIN_ID, "texts").getExtensions()); //$NON-NLS-1$
  }

  public CharacterTextContainer(IPluginExtension... extensions) {
    this.extensions = extensions;
  }

  public ICharacterText getText(final String id) {
    return new ClassConveyerBelt<ICharacterText>(
        IReportPluginConstants.PLUGIN_ID,
        ICharacterText.class,
        new IdAttributePredicate(id),
        extensions).getFirstObject();
  }
}