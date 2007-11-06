package net.sf.anathema.character.importwizard.internal;

import java.io.IOException;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.basics.eclipse.runtime.IProvider;
import net.sf.anathema.character.core.create.CharacterFactory;
import net.sf.anathema.character.importwizard.IModelImporter;
import net.sf.anathema.character.importwizard.plugin.CharacterImportWizardPluginConstants;

import org.dom4j.Document;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class TemplateImporter extends AbstractExecutableExtension implements IModelImporter {

  public IStatus runImport(IContainer container, Document document) throws IOException, CoreException {
    IProvider<String> provider = new TemplateConverter(document);
    if (provider.get() == null) {
      return new Status(
          IStatus.WARNING,
          CharacterImportWizardPluginConstants.PLUGIN_ID,
          Messages.CharacterTemplateConverter_UnsupportedTemplateWarning);
    }
    new CharacterFactory().saveTemplate(container, provider);
    return Status.OK_STATUS;
  }
}