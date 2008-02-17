package net.sf.anathema.character.experience.importwizard;

import java.io.IOException;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.importwizard.IModelImporter;
import net.sf.anathema.character.importwizard.ModelImporter;

import org.dom4j.Document;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;

public class ExperienceImporter extends AbstractExecutableExtension implements IModelImporter {

  private final ModelImporter importer = new ModelImporter(new ExperienceConverter(), IExperience.MODEL_ID);

  @Override
  public IStatus runImport(IContainer container, Document document) throws IOException, CoreException {
    return importer.runImport(container, document);
  }
}