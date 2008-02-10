package net.sf.anathema.character.textreport.encoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.disy.commons.core.util.CollectionUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.extension.Messages;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.character.textreport.IPluginConstants;

public class TextEncoderExtensionPoint {

  private static final String ATTRIB_ID = "id";
  private static final String ATTRIB_REFERENCE_ID = "referenceId";
  private static final String ATTRIB_CLASS = "class";
  private final IPluginExtension[] pluginExtensions;

  private static class TextEncoderConfiguration {
    private final ITextReportEncoder encoder;
    private final String id;
    private final List<String> afterIds = new ArrayList<String>();

    public TextEncoderConfiguration(ITextReportEncoder encoder, String id) {
      this.encoder = encoder;
      this.id = id;
    }

    public void addAfterId(String after) {
      afterIds.add(after);
    }

    public boolean containsAfter(String after) {
      return afterIds.contains(after);
    }

    public String getId() {
      return id;
    }

    public ITextReportEncoder getEncoder() {
      return encoder;
    }
  }

  public TextEncoderExtensionPoint() {
    this(new EclipseExtensionPoint(IPluginConstants.PLUGIN_ID, "textencoder").getExtensions()); //$NON-NLS-1$
  }

  public TextEncoderExtensionPoint(IPluginExtension... pluginExtensions) {
    this.pluginExtensions = pluginExtensions;
  }

  public List<ITextReportEncoder> getEncoders() {
    List<TextEncoderConfiguration> allObjects = getAllObjects();
    Collections.sort(allObjects, new Comparator<TextEncoderConfiguration>() {
      @Override
      public int compare(TextEncoderConfiguration o1, TextEncoderConfiguration o2) {
        if (o1.containsAfter(o2.getId())) {
          return 1;
        }
        if (o2.containsAfter(o1.getId())) {
          return -1;
        }
        return 0;
      }
    });
    return CollectionUtilities.transform(allObjects, new ITransformer<TextEncoderConfiguration, ITextReportEncoder>() {
      @Override
      public ITextReportEncoder transform(TextEncoderConfiguration configuration) {
        return configuration.getEncoder();
      }
    });
  }

  private List<TextEncoderConfiguration> getAllObjects() {
    List<TextEncoderConfiguration> allObjects = new ArrayList<TextEncoderConfiguration>();
    for (IPluginExtension extension : pluginExtensions) {
      for (IExtensionElement element : extension.getElements()) {
        try {
          ITextReportEncoder encoder = element.getAttributeAsObject(ATTRIB_CLASS, ITextReportEncoder.class);
          TextEncoderConfiguration configuration = new TextEncoderConfiguration(
              encoder,
              element.getAttribute(ATTRIB_ID));
          for (IExtensionElement child : element.getElements()) {
            configuration.addAfterId(child.getAttribute(ATTRIB_REFERENCE_ID));
          }
          allObjects.add(configuration);
        }
        catch (Exception e) {
          Logger logger = new Logger(IPluginConstants.PLUGIN_ID);
          logger.error(Messages.ClassConveyerBelt_InstantiationException, e);
        }
      }
    }
    return allObjects;
  }
}