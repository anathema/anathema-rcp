package net.sf.anathema.character.core.template;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.logging.ILogger;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.modellist.IModelListProvider;
import net.sf.anathema.character.core.modellist.ModelListProvider;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;

public class CharacterTemplateProvider implements ICharacterTemplateProvider {

  private static final String ATTRIB_CHARACTER_TYPE_ID = "characterTypeId"; //$NON-NLS-1$
  public static final String TEMPLATE_FILE_NAME = "template.xml"; //$NON-NLS-1$
  public static final String ATTRIB_REFERENCE = "reference"; //$NON-NLS-1$
  private static final String ATTRIB_MODEL_LIST_ID = "modelListId"; //$NON-NLS-1$
  private static final Pattern REFERENCE_PATTERN = Pattern.compile(ATTRIB_REFERENCE + "=\"(.*)\""); //$NON-NLS-1$
  private static final String ATTRIB_TEMPLATE_ID = "templateId"; //$NON-NLS-1$
  private static final String ATTRIB_NAME = "name"; //$NON-NLS-1$
  private static final String TEMPLATES_EXTENSION_POINT = "templates"; //$NON-NLS-1$
  private static final ILogger logger = new Logger(CharacterCorePlugin.ID);
  private final List<ICharacterTemplate> allTemplates = new ArrayList<ICharacterTemplate>();

  public CharacterTemplateProvider() {
    this(
        new EclipseExtensionPoint(CharacterCorePlugin.ID, TEMPLATES_EXTENSION_POINT).getExtensions(),
        new ModelListProvider());
  }

  public CharacterTemplateProvider(IPluginExtension[] extensions, IModelListProvider modelListProvider) {
    for (IPluginExtension extension : extensions) {
      for (IExtensionElement templateElement : extension.getElements()) {
        String templateId = templateElement.getAttribute(ATTRIB_TEMPLATE_ID);
        String untitledLabel = templateElement.getAttribute(ATTRIB_NAME);
        String characterTypeId = templateElement.getAttribute(ATTRIB_CHARACTER_TYPE_ID);
        CharacterTemplate template = new CharacterTemplate(templateId, untitledLabel, characterTypeId);
        allTemplates.add(template);
        for (IExtensionElement modelListElement : templateElement.getElements()) {
          String modelListId = modelListElement.getAttribute(ATTRIB_MODEL_LIST_ID);
          template.addModelList(modelListProvider.getModelList(modelListId));
        }
      }
    }
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

  @Override
  public ICharacterTemplate getTemplate(String templateId) {
    for (ICharacterTemplate template : allTemplates) {
      if (template.getId().equals(templateId)) {
        return template;
      }
    }
    return null;
  }

  private String getTemplateReference(IContentHandle content) {
    if (!content.exists()) {
      return null;
    }
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

  public List<ICharacterTemplate> getAllTemplates() {
    return Collections.unmodifiableList(allTemplates);
  }
}