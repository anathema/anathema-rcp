package net.sf.anathema.basics.pdfexport.item;

import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.model.ObjectModel;
import net.sf.anathema.basics.pdfexport.writer.IExportItem;

import org.easymock.EasyMock;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.Test;

public class ExportItemDialogPage_DoubleClickTest {
  private ExportItemDialogPage<Object> page;
  private List<IExportItem<Object>> exportItems;
  private ObjectModel<IExportItem<Object>> selectedItem;
  private IWizardContainer wizardContainer;

  @Before
  public void createPage() {
    DummyExportItem<Object> exportItem = new DummyExportItem<Object>(null, null);
    exportItems = new ArrayList<IExportItem<Object>>();
    exportItems.add(exportItem);
    selectedItem = new ObjectModel<IExportItem<Object>>();
    selectedItem.setValue(exportItem);
    page = new ExportItemDialogPage<Object>(exportItems, selectedItem);
    wizardContainer = createNiceMock(IWizardContainer.class);
    IWizard wizard = createMock(IWizard.class);
    expect(wizard.getContainer()).andReturn(wizardContainer).anyTimes();
    IWizardPage nextPage = EasyMock.createMock(IWizardPage.class);
    expect(wizard.getNextPage(page)).andReturn(nextPage).anyTimes();
    replay(wizard);
    IWizard createWizard = wizard;
    page.setWizard(createWizard);
  }

  @Test
  public void switchesPageOnDoubleClick() throws Exception {
    wizardContainer.showPage(page.getNextPage());
    replay(wizardContainer);
    page.createControl(new Shell());
    page.getControl().notifyListeners(SWT.DefaultSelection, new Event());
    verify(wizardContainer);
  }
}
