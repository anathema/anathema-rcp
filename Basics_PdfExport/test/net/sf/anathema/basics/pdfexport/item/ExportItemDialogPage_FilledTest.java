package net.sf.anathema.basics.pdfexport.item;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.model.ObjectModel;
import net.sf.anathema.basics.pdfexport.writer.IExportItem;

import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.Test;

public class ExportItemDialogPage_FilledTest {

  private static final String SECOND_NAME = "second"; //$NON-NLS-1$
  private static final String FIRST_NAME = "first"; //$NON-NLS-1$
  private static final DummyExportItem<Object> FIRST_ITEM = new DummyExportItem<Object>(FIRST_NAME, new Object());
  private static final DummyExportItem<Object> SECOND_ITEM = new DummyExportItem<Object>(SECOND_NAME, new Object());
  private ExportItemDialogPage<Object> page;
  private List<IExportItem<Object>> exportItems;
  private ObjectModel<IExportItem<Object>> selectedItem;
  private IWizardContainer wizardContainer;
  private Shell shell;

  @Before
  public void createPage() {
    exportItems = new ArrayList<IExportItem<Object>>();
    exportItems.add(FIRST_ITEM);
    exportItems.add(SECOND_ITEM);
    selectedItem = new ObjectModel<IExportItem<Object>>();
    page = new ExportItemDialogPage<Object>(exportItems, selectedItem);
    wizardContainer = createNiceMock(IWizardContainer.class);
    page.setWizard(WizardObjectMother.createWizard(wizardContainer));
    shell = new Shell();
    page.createControl(shell);
  }

  @Test
  public void putsFirstItemPrintNameFirstInList() throws Exception {
    assertEquals(FIRST_NAME, getAddedList().getItem(0));
  }

  @Test
  public void putsSecondItemPrintNameSecondInList() throws Exception {
    assertEquals(SECOND_NAME, getAddedList().getItem(1));
  }

  private org.eclipse.swt.widgets.List getAddedList() {
    return (org.eclipse.swt.widgets.List) shell.getChildren()[0];
  }
}