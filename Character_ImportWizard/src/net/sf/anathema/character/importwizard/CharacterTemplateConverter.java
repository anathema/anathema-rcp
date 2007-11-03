package net.sf.anathema.character.importwizard;

import java.io.IOException;

import net.sf.anathema.basics.eclipse.runtime.IProvider;
import net.sf.anathema.character.core.create.CharacterFactory;

import org.dom4j.Document;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;

public class CharacterTemplateConverter {

  private final IFolder folder;

  public CharacterTemplateConverter(IFolder folder) {
    this.folder = folder;
  }

  public void convert(Document document) throws IOException, CoreException {
    IProvider<String> provider = new TemplateConvertingProvider(document);
    new CharacterFactory().saveTemplate(folder, provider);
  }
}