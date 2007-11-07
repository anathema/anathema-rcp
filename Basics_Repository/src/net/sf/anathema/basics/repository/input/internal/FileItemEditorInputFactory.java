package net.sf.anathema.basics.repository.input.internal;

import java.net.URL;

import net.sf.anathema.basics.item.editor.ErrorMessageEditorInput;

import org.eclipse.core.resources.IContainer;
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
  private final IContainer root;

  public FileItemEditorInputFactory() {
    this(ResourcesPlugin.getWorkspace().getRoot());
  }

  public FileItemEditorInputFactory(IContainer root) {
    this.root = root;
  }

  @Override
  public IAdaptable createElement(IMemento memento) {
    IPath path = new Path(memento.getString(PROP_FULL_PATH));
    IFile file = root.getFile(path);
    try {
      file.refreshLocal(0, null);
      if (!file.exists()) {
        return new ErrorMessageEditorInput(Messages.FileItemEditorInputFactory_FileNotFoundMessage, file);
      }
      String untitledName = memento.getString(PROP_UNTITLED_NAME);
      URL imageUrl = new URL(memento.getString(PROP_IMAGE_DESCRIPTOR_URL));
      return new FileItemEditorInput(file, untitledName, imageUrl);
    }
    catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}