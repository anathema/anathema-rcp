package net.sf.anathema.basics.pdfexport.item;

import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.model.ObjectModel;
import net.sf.anathema.basics.pdfexport.writer.IExportItem;

import org.eclipse.jface.wizard.IWizardContainer;
import org.junit.Before;
import org.junit.Test;

public class ExportItemDialogPage_InteractionTest {

  private ExportItemDialogPage<Object> page;
  private List<IExportItem<Object>> exportItems;
  private ObjectModel<IExportItem<Object>> selectedItem;
  private IWizardContainer wizardContainer;

  @Before
  public void createPage() {
    exportItems = new ArrayList<IExportItem<Object>>();
    selectedItem = new ObjectModel<IExportItem<Object>>();
    page = new ExportItemDialogPage<Object>(exportItems, selectedItem);
    wizardContainer = createNiceMock(IWizardContainer.class);
    page.setWizard(WizardObjectMother.createWizard(wizardContainer));
  }

  @Test
  public void updatesButtonsOnSelectedItemChange() throws Exception {
    wizardContainer.updateButtons();
    replay(wizardContainer);
    selectedItem.setValue(new DummyExportItem<Object>(null, null));
    verify(wizardContainer);
  }
}