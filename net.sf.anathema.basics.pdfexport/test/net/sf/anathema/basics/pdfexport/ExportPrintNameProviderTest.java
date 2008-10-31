package net.sf.anathema.basics.pdfexport;

import static org.junit.Assert.*;
import net.disy.commons.core.model.ObjectModel;
import net.sf.anathema.basics.pdfexport.item.DummyExportItem;
import net.sf.anathema.basics.pdfexport.writer.IExportItem;

import org.junit.Before;
import org.junit.Test;

public class ExportPrintNameProviderTest {

  private ObjectModel<IExportItem<Object>> selectionModel;
  private ExportPrintNameProvider<Object> provider;

  @Before
  public void createProvider() {
    selectionModel = new ObjectModel<IExportItem<Object>>();
    provider = new ExportPrintNameProvider<Object>(selectionModel);
  }

  @Test
  public void returnsNullForNoSetExportItem() throws Exception {
    assertNull(provider.get());
  }

  @Test
  public void returnsPrintNameOfSetExportItem() throws Exception {
    selectionModel.setValue(new DummyExportItem<Object>("PrintName", new Object())); //$NON-NLS-1$
    assertEquals("PrintName", provider.get()); //$NON-NLS-1$
  }
}