package net.sf.anathema.character.sheet.pdf;

import java.text.MessageFormat;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.logging.ILogger;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.sheet.common.IDynamicPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.IContentEncoderProvider;
import net.sf.anathema.character.sheet.plugin.CharacterSheetPluginConstants;

public class RegisteredContentEncoderProvider implements IContentEncoderProvider {

  private static final String ATTRIB_CLASS = "class"; //$NON-NLS-1$
  private static final String ATTRIB_FIELD_ID = "fieldId"; //$NON-NLS-1$
  private static final String ENCODERS_EXTENSION_POINT = "encoders"; //$NON-NLS-1$
  private final IPluginExtension[] extensions;
  private final ILogger logger;

  public RegisteredContentEncoderProvider() {
    this(new Logger(CharacterSheetPluginConstants.PLUGIN_ID), new EclipseExtensionPoint(
        CharacterSheetPluginConstants.PLUGIN_ID,
        ENCODERS_EXTENSION_POINT).getExtensions());
  }

  public RegisteredContentEncoderProvider(ILogger logger, IPluginExtension... extensions) {
    this.logger = logger;
    this.extensions = extensions;
  }

  @Override
  public IPdfContentBoxEncoder getContentEncoder(String encoderName, IModelContainer character) {
    for (IPluginExtension extension : extensions) {
      for (IExtensionElement element : extension.getElements()) {
        String fieldId = element.getAttribute(ATTRIB_FIELD_ID);
        if (fieldId.equals(encoderName)) {
          try {
            return element.getAttributeAsObject(ATTRIB_CLASS, IPdfContentBoxEncoder.class);
          }
          catch (ExtensionException e) {
            logger.error(MessageFormat.format(
                Messages.RegisteredContentEncoderProvider_RETRIEVING_ENCODER_ERROR_MESSAGE,
                new Object[] { encoderName }), e);
          }
        }
      }
    }
    return null;
  }

  @Override
  public IDynamicPdfContentBoxEncoder getDynamicContentEncoder(String encoderName, IModelContainer character) {
    IPdfContentBoxEncoder contentEncoder = getContentEncoder(encoderName, character);
    if (contentEncoder == null) {
      return null;
    }
    if (contentEncoder instanceof IDynamicPdfContentBoxEncoder) {
      return (IDynamicPdfContentBoxEncoder) contentEncoder;
    }
    return new DefaultHeightDynamicEncoder(contentEncoder);
  }
}