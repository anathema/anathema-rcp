package net.sf.anathema.character.textreport.encoder;

import net.sf.anathema.character.core.character.ICharacter;

import org.eclipse.core.runtime.IExecutableExtension;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;

public interface ITextReportEncoder extends IExecutableExtension {

  public Iterable<Element> createParagraphs(ICharacter character) throws DocumentException;
  
  public String getModelId();
}