package net.sf.anathema.character.importwizard;

import java.io.IOException;

import net.sf.anathema.basics.eclipse.runtime.IProvider;
import net.sf.anathema.character.core.create.CharacterFactory;
import net.sf.anathema.character.importwizard.plugin.CharacterImportWizardPluginConstants;

import org.dom4j.Document;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class TemplateImporter {

  private final IContainer container;

  public TemplateImporter(IContainer container) {
    this.container = container;
  }

  public IStatus runImport(Document document) throws IOException, CoreException {
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