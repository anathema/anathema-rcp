package net.sf.anathema.character.textreport;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.character.textreport.encoder.ITextReportEncoder;

public class TextEncoderExtensionPoint {

  private Logger logger = new Logger(IPluginConstants.PLUGIN_ID);
  private static final String ATTRIB_CLASS = "class"; //$NON-NLS-1$
  private final IPluginExtension[] pluginExtensions;

  public TextEncoderExtensionPoint() {
    this(new EclipseExtensionPoint(IPluginConstants.PLUGIN_ID, "textencoder").getExtensions()); //$NON-NLS-1$
  }

  public TextEncoderExtensionPoint(IPluginExtension... pluginExtensions) {
    this.pluginExtensions = pluginExtensions;
  }

  public List<ITextReportEncoder> getEncoders() {
    List<ITextReportEncoder> encoders = new ArrayList<ITextReportEncoder>();
    for (IPluginExtension extension : pluginExtensions) {
      for (IExtensionElement element : extension.getElements()) {
        try {
          encoders.add(element.getAttributeAsObject(ATTRIB_CLASS, ITextReportEncoder.class));
        }
        catch (ExtensionException e) {
          logger.error(Messages.TextEncoderExtensionPoint_ErrorMessage, e);
        }
      }
    }
    return encoders;
  }
}