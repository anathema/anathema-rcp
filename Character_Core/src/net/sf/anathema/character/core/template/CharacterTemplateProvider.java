package net.sf.anathema.character.core.template;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.plugin.ICharacterCorePluginConstants;


public class CharacterTemplateProvider implements ICharacterTemplateProvider {

  private static final Logger logger = new Logger(ICharacterCorePluginConstants.PLUGIN_ID);
  public static final String TEMPLATE_FILE_NAME = "template.xml"; //$NON-NLS-1$
  public static String STATIC_TEMPLATE_ID = "net.sf.anathema.core.StaticTemplate"; //$NON-NLS-1$
  public static final String ATTRIB_REFERENCE = "reference"; //$NON-NLS-1$
  private static final Pattern REFERENCE_PATTERN = Pattern.compile(ATTRIB_REFERENCE + "=\"(.*)\""); //$NON-NLS-1$
  private List<ICharacterTemplate> allTemplates = new ArrayList<ICharacterTemplate>();

  public CharacterTemplateProvider() {
    allTemplates.add(new ICharacterTemplate() {

      public String getId() {
        return STATIC_TEMPLATE_ID;
      }

      @Override
      public boolean supportsModel(String modelId) {
        return true;
      }
    });
  }

  @Override
  public boolean isTemplateAvailable(ICharacterId characterId) {
    return getTemplate(characterId) != null;
  }

  @Override
  public ICharacterTemplate getTemplate(ICharacterId characterId) {
    IContentHandle content = characterId.getContents(TEMPLATE_FILE_NAME);
    String templateReference = getTemplateReference(content);
    for (ICharacterTemplate template : allTemplates) {
      if (template.getId().equals(templateReference)) {
        return template;
      }
    }
    return null;
  }

  private String getTemplateReference(IContentHandle content) {
    InputStreamReader reader = null;
    try {
      reader = new InputStreamReader(content.getContents());
      String contentString = IOUtilities.readString(reader);
      Matcher printNameMatcher = REFERENCE_PATTERN.matcher(contentString);
      if (!printNameMatcher.find()) {
        throw new IllegalStateException("Illegal resource format: No template reference defined."); //$NON-NLS-1$
      }
      return printNameMatcher.group(1);
    }
    catch (Exception e) {
      String message = Messages.CharacterTemplateProvider_NoTemplateReferenceMessage;
      logger.error(message, e);
      return null;
    }
    finally {
      IOUtilities.close(reader);
    }
  }
}