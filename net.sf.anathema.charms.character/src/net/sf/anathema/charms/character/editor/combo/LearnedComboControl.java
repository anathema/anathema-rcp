package net.sf.anathema.charms.character.editor.combo;

import static net.disy.commons.core.string.StringConcatenationBuilder.createConcatenatedString;
import static net.disy.commons.core.util.CollectionUtilities.transform;

import java.util.List;

import net.sf.anathema.charms.character.combo.Combo;
import net.sf.anathema.charms.data.INameMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class LearnedComboControl {

  private final Composite composite;
  private final FormToolkit toolkit;
  private final INameMap charmNameMap;

  public LearnedComboControl(Composite composite, FormToolkit toolkit, INameMap charmNameMap) {
    this.composite = composite;
    this.toolkit = toolkit;
    this.charmNameMap = charmNameMap;
  }

  public void updateView(Iterable<Combo> combos) {
    clearView();
    toolkit.createLabel(composite, "Learned Combos:");
    for (Combo combo : combos) {
      StyledText styledText = new StyledText(composite, SWT.BORDER | SWT.MULTI);
      styledText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));
      fillComboInto(styledText, combo);
    }
    composite.layout();
  }

  private void fillComboInto(StyledText styledText, Combo combo) {
    StringBuffer comboText = new StringBuffer();
    comboText.append(combo.name == null ? "Unnamed Combo" : combo.name);
    comboText.append("\n");
    if (combo.description != null) {
      comboText.append(combo.description);
      comboText.append("\n");
    }
    List<String> charmNames = transform(combo.charms, new ToNameTransformer(charmNameMap));
    comboText.append(createConcatenatedString(",", charmNames));
    styledText.setText(comboText.toString());
  }

  private void clearView() {
    for (Control child : composite.getChildren()) {
      child.dispose();
    }
  }
}