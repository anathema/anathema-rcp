package net.sf.anathema.charms.character;

import net.sf.anathema.basics.swt.dispose.ColorDisposable;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.charms.view.ICharmNode;
import net.sf.anathema.charms.view.ICharmSelectionControl;
import net.sf.anathema.charms.view.ICharmSelectionListener;
import net.sf.anathema.charms.view.ICharmVisuals;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class CharacterCharmVisuals extends AggregatedDisposable implements ICharmVisuals {

  private Color learnedColor;
  private Color defaultColor;
  final ICharmModel charmModel;
  final IExperience experience;

  public CharacterCharmVisuals(ICharmModel charmModel, IExperience experience) {
    this.charmModel = charmModel;
    this.experience = experience;
  }

  @Override
  public void connect(final ICharmSelectionControl selectionControl) {
    final ICharmSelectionListener selectionListener = new LearningCharmSelectionListener(charmModel, experience);
    addDisposable(new IDisposable() {
      @Override
      public void dispose() {
        selectionControl.removeSelectionListener(selectionListener);
      }
    });
    selectionControl.addSelectionListener(selectionListener);
  }

  @Override
  public synchronized void update(ICharmNode node) {
    Display display = node.getDisplay();
    String charmId = node.getCharmId();
    node.setColor(isLearned(charmId) ? getLearnedColor(display) : getDefaultColor(display));
  }

  private boolean isLearned(String charmId) {
    return experience.isExperienced() ? charmModel.isLearned(charmId) : charmModel.isCreationLearned(charmId);
  }

  private synchronized Color getLearnedColor(Display display) {
    if (learnedColor == null) {
      learnedColor = new Color(display, 255, 215, 0);
      addDisposable(new ColorDisposable(learnedColor));
    }
    return learnedColor;
  }

  private synchronized Color getDefaultColor(Display display) {
    if (defaultColor == null) {
      defaultColor = new Color(display, 255, 255, 255);
      addDisposable(new ColorDisposable(defaultColor));
    }
    return defaultColor;
  }
}