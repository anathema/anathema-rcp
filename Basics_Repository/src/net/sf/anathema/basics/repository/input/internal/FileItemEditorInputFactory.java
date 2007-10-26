package net.sf.anathema.basics.repository.input.internal;

import java.net.URL;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;

public class FileItemEditorInputFactory implements IElementFactory {

  public static final String PROP_IMAGE_DESCRIPTOR_URL = "imageDescriptorUrl"; //$NON-NLS-1$
  public static final String PROP_UNTITLED_NAME = "untitledName"; //$NON-NLS-1$
  public static final String PROP_FULL_PATH = "fullPath"; //$NON-NLS-1$

  @Override
  public IAdaptable createElement(IMemento memento) {
    IPath path = new Path(memento.getString(PROP_FULL_PATH));
    IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
    String untitledName = memento.getString(PROP_UNTITLED_NAME);
    try {
      URL imageUrl = new URL(memento.getString(PROP_IMAGE_DESCRIPTOR_URL));
      return new FileItemEditorInput(file, untitledName, imageUrl);
    }
    catch (Exception e) {
      // TODO Errorhandling (UrlException separat?)
      return null;
    }
  }
}