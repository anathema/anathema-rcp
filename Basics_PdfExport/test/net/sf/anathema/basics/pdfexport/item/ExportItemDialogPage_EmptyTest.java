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

public class ExportItemDialogPage_EmptyTest {

  private static final int LIST_INDEX = 0;
  private Shell parent;

  @Before
  public void createContentOfEmptyPage() {
    List<IExportItem<Object>> exportItems = new ArrayList<IExportItem<Object>>();
    ObjectModel<IExportItem<Object>> selectedItem = new ObjectModel<IExportItem<Object>>();
    ExportItemDialogPage<Object> page = new ExportItemDialogPage<Object>(exportItems, selectedItem);
    page.setWizard(WizardObjectMother.createWizard(createNiceMock(IWizardContainer.class)));
    parent = new Shell();
    page.createControl(parent);
  }

  @Test
  public void hasAddedOnlyOneChildToParent() throws Exception {
    assertEquals(1, parent.getChildren().length);
  }

  @Test
  public void hasAddedEmptyListToParent() throws Exception {
    org.eclipse.swt.widgets.List list = (org.eclipse.swt.widgets.List) parent.getChildren()[LIST_INDEX];
    assertEquals(0, list.getItemCount());
  }
}