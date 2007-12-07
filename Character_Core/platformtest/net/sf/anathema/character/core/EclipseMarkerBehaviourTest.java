package net.sf.anathema.character.core;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;

import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.character.core.create.CharacterRepositoryUtilities;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EclipseMarkerBehaviourTest {

  private IFile file;

  @Before
  public void createFile() throws Exception {
    IProject characterProject = RepositoryUtilities.getProject(CharacterRepositoryUtilities.getCharacterItemType());
    file = characterProject.getFile("hondo.txt"); //$NON-NLS-1$
    file.create(new ByteArrayInputStream(new byte[0]), false, new NullProgressMonitor());
  }

  @Test
  public void markersMustNotBeRegistered() throws Exception {
    String identifier = "eclipse kennt nicht all hasäntümer"; //$NON-NLS-1$
    file.createMarker(identifier);
    IMarker[] foundMarkers = file.findMarkers(identifier, false, IResource.DEPTH_ZERO);
    assertEquals(1, foundMarkers.length);
  }

  @After
  public void deleteFile() throws Exception {
    file.delete(true, new NullProgressMonitor());
  }
}