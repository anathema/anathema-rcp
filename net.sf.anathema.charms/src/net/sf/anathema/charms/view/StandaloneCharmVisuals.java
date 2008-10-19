package net.sf.anathema.charms.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class StandaloneCharmVisuals implements ICharmVisuals {

  private Color learnedColor;
  private Color defaultColor;
  private final List<String> learnedCharms = new ArrayList<String>();

  @Override
  public void connect(ICharmSelectionControl selectionControl) {
    selectionControl.addSelectionListener(new ICharmSelectionListener() {
      @Override
      public void charmSelected(String charmId) {
        if (learnedCharms.contains(charmId)) {
          learnedCharms.remove(charmId);
        }
        else {
          learnedCharms.add(charmId);
        }
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
    return learnedCharms.contains(charmId);
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