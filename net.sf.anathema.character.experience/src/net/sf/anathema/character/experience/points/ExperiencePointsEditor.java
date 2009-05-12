package net.sf.anathema.character.experience.points;

import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.basics.item.editor.IEditorControl;
import net.sf.anathema.character.core.editors.AbstractCharacterModelEditorPart;
import net.sf.anathema.character.experience.IExperiencePoints;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class ExperiencePointsEditor extends AbstractCharacterModelEditorPart<IExperiencePoints> {

  @Override
  protected IEditorControl createItemEditorControl() {
    return new AbstractItemEditorControl(this) {

      @Override
      public void setFocus() {
        // nothing to do
      }

      @Override
      public void createPartControl(Composite parent) {
        parent.setLayout(new GridLayout(2, false));
        new Label(parent, SWT.NONE).setText("Hasä");
      }
    };
  }
}