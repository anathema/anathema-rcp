package net.sf.anathema.tools.conversion;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.disy.commons.core.creation.IFactory;
import net.sf.anathema.basics.importexport.XSLDocumentConverter;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;

public class CharmStructureConverter {

  public static void main(String[] args) throws Exception {
    File sourceFolder = new File(args[0]);
    File targetFolder = new File(args[1]);
    Map<String, String> parameters = new HashMap<String, String>();
    IFactory<InputStream, IOException> streamFactory = new StructureSheetStreamFactory();
    XSLDocumentConverter converter = new XSLDocumentConverter(streamFactory, parameters);
    for (File charmFile : sourceFolder.listFiles()) {
      Document sourceDocument = DocumentUtilities.read(charmFile);
      Document targetDocument = converter.run(sourceDocument);
      File targetFile = createTargetFile(targetFolder, charmFile);
      DocumentUtilities.save(targetDocument, targetFile);
    }
  }

  private static File createTargetFile(File targetFolder, File sourceFile) {
    String name = sourceFile.getName();
    String targetName = "Structure" + name.substring(name.lastIndexOf("_") + 1); //$NON-NLS-1$//$NON-NLS-2$
    return new File(targetFolder, targetName);
  }
}