package net.sf.anathema.campaign.note.importwizard;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.InputStream;

import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NoteImportWizardTest {
  private static final String PATH = "net/sf/anathema/campaign/note/importwizard/"; //$NON-NLS-1$  
  private NoteImportWizard wizard;
  private IFile internalFile;
  private IProject project;

  @Before
  public void createWizard() throws Exception {
    wizard = new NoteImportWizard();
  }

  @Before
  public void createInternalFile() throws Exception {
    project = ResourcesPlugin.getWorkspace().getRoot().getProject("testProject"); //$NON-NLS-1$
    project.create(new NullProgressMonitor());
    project.open(new NullProgressMonitor());
    Path path = new Path("newNote.not"); //$NON-NLS-1$
    internalFile = project.getFile(path);
  }

  @Test
  public void importsFileWithBundleVersion() throws Exception {
    File oldNote = new File(
        "B:\\Anathema\\ARC\\Campaign_Note_Importwizard\\test\\net\\sf\\anathema\\campaign\\note\\importwizard\\oldNote.not"); //$NON-NLS-1$
    wizard.runImport(oldNote, internalFile, new NullProgressMonitor());
    Document expectedDocument = DocumentUtilities.read(getStream("newNote.not")); //$NON-NLS-1$
    Document resultDocument = DocumentUtilities.read(internalFile.getContents());
    assertEquals(DocumentUtilities.asString(expectedDocument), DocumentUtilities.asString(resultDocument));
  }

  private InputStream getStream(String string) {
    return getClass().getClassLoader().getResourceAsStream(PATH + string);
  }

  @After
  public void deleteInternalFile() throws Exception {
    internalFile.delete(true, new NullProgressMonitor());
  }

  @After
  public void deleteProject() throws Exception {
    project.delete(true, new NullProgressMonitor());
  }
}