package net.sf.anathema.basics.repository.treecontent;

import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.basics.repository.RepositoryPlugin;
import net.sf.anathema.framework.editor.FileItemEditorInput;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class ResourceViewElement implements IViewElement {
  public static final String ENCODING = "ISO-8859-1"; //$NON-NLS-1$
  private static final Pattern PRINT_NAME_PATTERN = Pattern.compile("repositoryPrintName=\"(.*?)\""); //$NON-NLS-1$
  private final IResource resource;
  private final IViewElement parent;

  public ResourceViewElement(IResource resource, IViewElement parent) {
    this.resource = resource;
    this.parent = parent;
  }

  @Override
  public Object[] getChildren() {
    return new Object[0];
  }

  @Override
  public String getDisplayName() {
    IFile file = (IFile) resource;
    InputStreamReader reader = null;
    try {
      reader = new InputStreamReader(file.getContents());
      String content = IOUtilities.readString(reader);
      Matcher printNameMatcher = PRINT_NAME_PATTERN.matcher(content);
      if (!printNameMatcher.find()) {
        throw new IllegalStateException("Illegal resource format: No display name defined."); //$NON-NLS-1$
      }
      return printNameMatcher.group(1);
    }
    catch (Exception e) {
      RepositoryPlugin.log(IStatus.ERROR, "Error reading display name.", e);
      e.printStackTrace();
      return resource.getName();
    }
    finally {
      IOUtilities.close(reader);
    }
  }

  @Override
  public Image getImage() {
    return parent.getImage();
  }

  @Override
  public Object getParent() {
    return parent;
  }

  @Override
  public boolean hasChildren() {
    return false;
  }

  @Override
  public void openEditor(IWorkbenchPage page) throws PartInitException {
    IFile file = (IFile) resource;
    IEditorInput input = new FileItemEditorInput(file);
    String fileName = file.getName();
    IEditorDescriptor defaultEditor = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(fileName);
    page.openEditor(input, defaultEditor.getId());
  }
}