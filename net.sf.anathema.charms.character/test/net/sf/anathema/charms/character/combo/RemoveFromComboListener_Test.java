package net.sf.anathema.charms.character.combo;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import net.sf.anathema.charms.character.editor.ComboEditModel;
import net.sf.anathema.charms.character.editor.combo.RemoveFromComboListener;
import net.sf.anathema.charms.tree.CharmId;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class RemoveFromComboListener_Test {

  private ComboEditModel model;

  @Before
  public void createEditModel() throws Exception {
    this.model = new ComboEditModel();
    this.model.addCharmToCombo(new CharmId("thisCharm", null));
  }

  @Test
  public void doesNothingOnEmptySelection() throws Exception {
    RemoveFromComboListener listener = new RemoveFromComboListener(model);
    StructuredSelection selection = new StructuredSelection();
    listener.doubleClick(new DoubleClickEvent(new TableViewer(new Shell(), SWT.NONE), selection));
    assertThat(model.createCombo().charms.size(), is(1));
  }

  @Test
  public void addsSelectedCharmToCombo() throws Exception {
    RemoveFromComboListener listener = new RemoveFromComboListener(model);
    StructuredSelection selection = new StructuredSelection(Arrays.asList(new CharmId("thisCharm", null)));
    listener.doubleClick(new DoubleClickEvent(new TableViewer(new Shell(), SWT.NONE), selection));
    assertThat(model.createCombo().charms.size(), is(0));
  }
}