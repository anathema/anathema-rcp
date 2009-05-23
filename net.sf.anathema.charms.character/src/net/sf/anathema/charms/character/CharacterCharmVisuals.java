package net.sf.anathema.charms.character;

import net.sf.anathema.basics.swt.dispose.ColorDisposable;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.charms.character.model.ICharmCharacter;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.character.preference.ICharmPreferences;
import net.sf.anathema.charms.tree.ICharmId;
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
  private final ICharmPreferences preferences;
  private final ICharmCharacter charmCharacter;

  public CharacterCharmVisuals(
      ICharmModel charmModel,
      IExperience experience,
      ICharmPreferences preferences,
      ICharmCharacter charmCharacter) {
    this.charmModel = charmModel;
    this.experience = experience;
    this.preferences = preferences;
    this.charmCharacter = charmCharacter;
  }

  @Override
  public void connect(final ICharmSelectionControl selectionControl) {
    final ICharmSelectionListener selectionListener = new LearningCharmSelectionListener(
        charmModel,
        experience,
        preferences,
        charmCharacter);
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
    ICharmId charmId = node.getCharmId();
    node.setColor(charmCharacter.isLearned(charmId) ? getLearnedColor(display) : getDefaultColor(display));
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