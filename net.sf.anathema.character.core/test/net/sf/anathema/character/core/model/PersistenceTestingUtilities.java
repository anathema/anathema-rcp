package net.sf.anathema.character.core.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.core.model.template.NullModelTemplate;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;

public class PersistenceTestingUtilities {

  public static <M extends IModel> M saveAndLoadWithoutTemplate(
      M points,
      IModelPersister<NullModelTemplate, M> persister) throws Exception {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    persister.save(outputStream, points);
    byte[] xmlAsBytes = outputStream.toByteArray();
    ByteArrayInputStream inputStream = new ByteArrayInputStream(xmlAsBytes);
    Document document = DocumentUtilities.read(inputStream);
    M loadedModel = persister.load(document, null);
    return loadedModel;
  }

}
