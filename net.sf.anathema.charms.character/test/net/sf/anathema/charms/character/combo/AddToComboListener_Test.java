package net.sf.anathema.charms.character.combo;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import net.sf.anathema.charms.character.editor.ComboEditModel;
import net.sf.anathema.charms.character.editor.combo.AddToComboListener;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class AddToComboListener_Test {

  private ComboEditModel model;

  @Before
  public void createEditModel() throws Exception {
    this.model = new ComboEditModel();
  }

  @Test
  public void doesNothingOnEmptySelection() throws Exception {
    AddToComboListener listener = new AddToComboListener(model);
    StructuredSelection selection = new StructuredSelection();
    listener.doubleClick(new DoubleClickEvent(new TableViewer(new Shell(), SWT.NONE), selection));
    assertThat(model.createCombo().charms.size(), is(0));
  }

  @Test
  public void addsSelectedCharmToCombo() throws Exception {
    AddToComboListener listener = new AddToComboListener(model);
    StructuredSelection selection = new StructuredSelection(Arrays.asList(new CharmId("thisCharm", null)));
    listener.doubleClick(new DoubleClickEvent(new TableViewer(new Shell(), SWT.NONE), selection));
    assertThat(model.createCombo().charms.size(), is(1));
    assertThat(model.createCombo().charms.get(0), is((ICharmId) new CharmId("thisCharm", null)));
  }
}