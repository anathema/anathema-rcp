package net.sf.anathema.character.importwizard.internal;

import java.io.IOException;

import org.dom4j.Document;
import org.eclipse.core.runtime.CoreException;

public class NullConverter implements IDocumentConverter {

  @Override
  public Document convert(Document document) throws CoreException, IOException {
    return document;
  }
}