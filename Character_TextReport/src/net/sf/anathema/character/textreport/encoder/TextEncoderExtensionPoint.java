package net.sf.anathema.character.textreport.encoder;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.ClassConveyerBelt;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.textreport.IPluginConstants;

public class TextEncoderExtensionPoint {

  private final IPluginExtension[] pluginExtensions;

  public TextEncoderExtensionPoint() {
    this(new EclipseExtensionPoint(IPluginConstants.PLUGIN_ID, "textencoder").getExtensions()); //$NON-NLS-1$
  }

  public TextEncoderExtensionPoint(IPluginExtension... pluginExtensions) {
    this.pluginExtensions = pluginExtensions;
  }

  public List<ITextReportEncoder> getEncoders() {
    return new ClassConveyerBelt<ITextReportEncoder>(
        IPluginConstants.PLUGIN_ID,
        ITextReportEncoder.class,
        pluginExtensions).getAllObjects();
  }
}