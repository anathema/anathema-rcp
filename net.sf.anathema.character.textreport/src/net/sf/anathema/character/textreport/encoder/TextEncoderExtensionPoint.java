package net.sf.anathema.character.textreport.encoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.extension.Messages;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.character.textreport.IPluginConstants;

public class TextEncoderExtensionPoint {
  private static final Logger logger = new Logger(IPluginConstants.PLUGIN_ID);
  private static final String ATTRIB_CLASS = "class"; //$NON-NLS-1$
  private final IPluginExtension[] pluginExtensions;

  public TextEncoderExtensionPoint() {
    this(new EclipseExtensionPoint(IPluginConstants.PLUGIN_ID, "textencoder").getExtensions()); //$NON-NLS-1$
  }

  public TextEncoderExtensionPoint(IPluginExtension... pluginExtensions) {
    this.pluginExtensions = pluginExtensions;
  }

  public List<ITextReportEncoder> getEncoders() {
    List<ITextReportEncoder> allObjects = getAllObjects();
    Collections.sort(allObjects);
    return allObjects;
  }

  private List<ITextReportEncoder> getAllObjects() {
    List<ITextReportEncoder> encoders = new ArrayList<ITextReportEncoder>();
    for (IPluginExtension extension : pluginExtensions) {
      for (IExtensionElement element : extension.getElements()) {
        try {
          encoders.add(element.getAttributeAsObject(ATTRIB_CLASS, ITextReportEncoder.class));
        }
        catch (Exception e) {
          logger.error(Messages.ClassConveyerBelt_InstantiationException, e);
        }
      }
    }
    return encoders;
  }
}