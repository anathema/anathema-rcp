package net.sf.anathema.charms.character;

import net.sf.anathema.charms.view.CharmSelectionControl;
import net.sf.anathema.charms.view.ICharmNode;
import net.sf.anathema.charms.view.ICharmSelectionListener;
import net.sf.anathema.charms.view.ICharmVisuals;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class CharacterCharmVisuals implements ICharmVisuals {

  private Color learnedColor;
  private Color defaultColor;
  private final ICharmModel charmModel;

  public CharacterCharmVisuals(ICharmModel charmModel) {
    this.charmModel = charmModel;
  }

  @Override
  public void connect(CharmSelectionControl selectionControl) {
    selectionControl.addSelectionListener(new ICharmSelectionListener() {
      @Override
      public void charmSelected(String charmId) {
        charmModel.toggleLearned(charmId);
      }
    });
  }

  @Override
  public synchronized void update(ICharmNode node) {
    Display display = node.getDisplay();
    String charmId = node.getCharmId();
    node.setColor(isLearned(charmId) ? getLearnedColor(display) : getDefaultColor(display));
  }

  private boolean isLearned(String charmId) {
    return charmModel.isLearned(charmId);
  }

  private synchronized Color getLearnedColor(Display display) {
    if (learnedColor == null) {
      learnedColor = new Color(display, 255, 215, 0);
    }
    return learnedColor;
  }

  private synchronized Color getDefaultColor(Display display) {
    if (defaultColor == null) {
      defaultColor = new Color(display, 255, 255, 255);
    }
    return defaultColor;
  }

  @Override
  public void dispose() {
    if (learnedColor != null) {
      learnedColor.dispose();
    }
    if (defaultColor != null) {
      defaultColor.dispose();
    }
  }
}