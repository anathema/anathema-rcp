package net.sf.anathema.character.report.text;

import net.sf.anathema.character.core.character.ICharacter;

import org.eclipse.core.runtime.IExecutableExtension;

public interface ICharacterText extends IExecutableExtension {

  public String getTextFor(ICharacter character);
  
  public boolean isActiveFor(ICharacter character);

  public String getLabel();
}