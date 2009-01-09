package net.sf.anathema.character.sheet.pdf;

import net.sf.anathema.basics.eclipse.extension.ClassConveyerBelt;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.sheet.common.IDynamicPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.IContentEncoderProvider;
import net.sf.anathema.character.sheet.plugin.CharacterSheetPluginConstants;

public class RegisteredContentEncoderProvider implements IContentEncoderProvider {

  private static final String ENCODERS_EXTENSION_POINT = "encoders"; //$NON-NLS-1$
  private final IPluginExtension[] extensions;

  public RegisteredContentEncoderProvider() {
    this(new EclipseExtensionPoint(CharacterSheetPluginConstants.PLUGIN_ID, ENCODERS_EXTENSION_POINT).getExtensions());
  }

  public RegisteredContentEncoderProvider(IPluginExtension... extensions) {
    this.extensions = extensions;
  }

  @Override
  public IPdfContentBoxEncoder getContentEncoder(final String encoderName, IModelContainer character) {
    return new ClassConveyerBelt<IPdfContentBoxEncoder>(
        CharacterSheetPluginConstants.PLUGIN_ID,
        IPdfContentBoxEncoder.class,
        new FieldIdPredicate(encoderName),
        extensions).getFirstObject();
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