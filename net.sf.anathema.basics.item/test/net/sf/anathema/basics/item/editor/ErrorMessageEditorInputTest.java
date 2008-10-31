package net.sf.anathema.basics.item.editor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ErrorMessageEditorInputTest {

  private ErrorMessageEditorInput editorInput;
  private String message;

  @Before
  public void createEditorInput() throws Exception {
    message = "Wir haben einen Fehler. Schubiduh"; //$NON-NLS-1$
    editorInput = new ErrorMessageEditorInput(message);
  }
  
  @Test
  public void doesNotExist() throws Exception {
    assertFalse(editorInput.exists());
  }
  
  @Test
  public void hasNoImageDescriptor() throws Exception {
    assertNull(editorInput.getImageDescriptor());
  }
  
  @Test
  public void hasName() throws Exception {
    assertNotNull(editorInput.getName());
  }
  
  @Test
  public void hasToolTip() throws Exception {
    assertNotNull(editorInput.getToolTipText());
  }
  
  @Test
  public void isNotPersistable() throws Exception {
    assertNull(editorInput.getPersistable());
  }
  
  @Test
  public void hasConstructedMessage() throws Exception {
    assertEquals(message, editorInput.getMessage());
  }
}