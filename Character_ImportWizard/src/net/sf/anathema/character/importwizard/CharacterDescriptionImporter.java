package net.sf.anathema.character.importwizard;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.sf.anathema.basics.eclipse.resource.FileWriter;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;

public class CharacterDescriptionImporter {

  private final IContainer folder;

  public CharacterDescriptionImporter(IContainer container) {
    this.folder = container;
  }

  public void runImport(Document document) throws IOException, CoreException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    DocumentUtilities.save(document, outputStream);
    new FileWriter().saveToFile(folder.getFile(new Path("basic.description")), outputStream, new NullProgressMonitor());
  }
}