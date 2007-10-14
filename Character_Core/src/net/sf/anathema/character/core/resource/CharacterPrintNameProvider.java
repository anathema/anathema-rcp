package net.sf.anathema.character.core.resource;

import net.sf.anathema.basics.repository.treecontent.itemtype.RegExPrintNameProvider;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Path;

public class CharacterPrintNameProvider {

  public String getPrintName(IContainer container, String fallback) {
    IFile descriptionFile = container.getFile(new Path("basic.description")); //$NON-NLS-1$
    return new RegExPrintNameProvider(fallback).getPrintName(descriptionFile);
  }
}