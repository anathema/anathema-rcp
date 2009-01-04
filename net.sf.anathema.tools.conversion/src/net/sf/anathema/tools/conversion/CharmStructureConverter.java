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
    File[] allFiles = sourceFolder.listFiles();
    System.err.print(sourceFolder.getCanonicalPath());
    for (File charmFile : allFiles) {
      if (!charmFile.getName().endsWith("xml")) {
        continue;
      }
      Document sourceDocument = DocumentUtilities.read(charmFile);
      Document targetDocument = converter.run(sourceDocument);
      File targetFile = createTargetFile(targetFolder, charmFile);
      DocumentUtilities.save(targetDocument, targetFile);
    }
  }

  private static File createTargetFile(File targetFolder, File sourceFile) {
    String sourceName = sourceFile.getName();
    String targetName = "Structure" + sourceName.substring(0, 1).toUpperCase() + sourceName.substring(1);
    return new File(targetFolder, targetName);
  }
}