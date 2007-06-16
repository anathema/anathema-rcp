package net.sf.anathema.character.attributes;

import java.io.IOException;

import net.sf.anathema.basics.jface.FileEditorInput;
import net.sf.anathema.basics.repository.input.IFileItemEditorInput;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;

public class AttributesEditorInput extends FileEditorInput implements IFileItemEditorInput<IAttributes> {

  private IAttributes attributes;

  public AttributesEditorInput(IFile file, ImageDescriptor imageDescriptor) {
    super(file, imageDescriptor);
    this.attributes = new Attributes();
  }

  @Override
  public IAttributes getItem() {
    return attributes;
  }

  @Override
  public IAttributes save(IProgressMonitor monitor) throws IOException, CoreException, PersistenceException {
    return attributes;
  }
}