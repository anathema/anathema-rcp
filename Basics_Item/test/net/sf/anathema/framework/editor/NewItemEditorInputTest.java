package net.sf.anathema.framework.editor;

import net.sf.anathema.framework.item.persistence.BasicDataItemPersister;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;

//TODO: Saven Testen
public class NewItemEditorInputTest {

  private NewItemEditorInput editorInput;

  @Before
  public void createFilledEditorInput() throws PersistenceException, CoreException {
    this.editorInput = new NewItemEditorInput();
    this.editorInput.loadItem(new BasicDataItemPersister());
  }
  
  //TODO: Hier gehts los.
  @Test
  public void createsNewFileOnSave() throws Exception {
    BasicDataItemPersister persister = new BasicDataItemPersister();
    editorInput.save(persister);
  }
  //TODO: Hier gehts weiter.
  
  public void deleteFiles() {
    
  }
}