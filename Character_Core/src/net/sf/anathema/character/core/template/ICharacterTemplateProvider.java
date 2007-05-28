package net.sf.anathema.character.core.template;

import org.eclipse.core.resources.IFolder;

public interface ICharacterTemplateProvider {

  public boolean isTemplateAvailable(IFolder characterFolder);
}