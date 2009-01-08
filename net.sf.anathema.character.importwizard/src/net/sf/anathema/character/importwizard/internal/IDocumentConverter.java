package net.sf.anathema.character.importwizard.internal;

import java.io.IOException;

import org.dom4j.Document;
import org.eclipse.core.runtime.CoreException;

public interface IDocumentConverter {

  public Document convert(Document document) throws CoreException, IOException;
}