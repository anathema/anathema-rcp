package net.sf.anathema.character.textreport.encoder;

import net.sf.anathema.character.core.character.ICharacter;

import org.eclipse.core.runtime.IExecutableExtension;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;

public interface ITextReportEncoder extends Comparable<ITextReportEncoder>, IExecutableExtension {

  public Iterable<Element> createParagraphs(ICharacter character) throws DocumentException;

  String getId();

  public boolean containsAfter(String id);
}