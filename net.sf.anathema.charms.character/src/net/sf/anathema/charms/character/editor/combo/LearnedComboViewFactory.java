package net.sf.anathema.charms.character.editor.combo;

import static net.disy.commons.core.string.StringConcatenationBuilder.createConcatenatedString;
import static net.disy.commons.core.util.CollectionUtilities.transform;

import java.util.List;

import net.sf.anathema.charms.character.combo.Combo;
import net.sf.anathema.charms.data.INameMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class LearnedComboViewFactory {

  private final Composite composite;
  private final INameMap charmNameMap;

  public LearnedComboViewFactory(Composite composite, INameMap charmNameMap) {
    this.composite = composite;
    this.charmNameMap = charmNameMap;
  }

  public void exchangeView(Iterable<Combo> combos) {
    clearView();
    for (Combo combo : combos) {
      StyledText styledText = new StyledText(composite, SWT.BORDER | SWT.MULTI);
      styledText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));
      fillComboInto(styledText, combo);
    }
    composite.layout();
  }

  private void fillComboInto(StyledText styledText, Combo combo) {
    StringBuffer comboText = new StringBuffer();
    String comboName = (combo.name == null ? "Unnamed Combo" : combo.name) + "\n";
    String description = combo.description != null ? combo.description + "\n" : "";
    comboText.append(comboName);
    comboText.append(description);
    List<String> charmNames = transform(combo.charms, new ToNameTransformer(charmNameMap));
    comboText.append(createConcatenatedString(",", charmNames));
    styledText.setText(comboText.toString());
    styledText.setStyleRanges(new StyleRange[] {
        new StyleRange(0, comboName.length(), null, null, SWT.BOLD),
        new StyleRange(comboName.length(), description.length(), null, null, SWT.ITALIC) });
  }

  private void clearView() {
    for (Control child : composite.getChildren()) {
      if (child instanceof StyledText) {
        child.dispose();
      }
    }
  }
}