package net.sf.anathema.character.trait;

import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public interface IBasicTrait {

  public void addCreationChangeListener(IChangeListener changeListener);

  public void addExperienceChangeListener(IChangeListener changeListener);
  
  public int getCreationValue();

  public int getExperiencedValue();
  
  public IIdentificate getTraitType();
  
  public void removeCreationChangeListener(IChangeListener changeListener);

  public void removeExperienceChangeListener(IChangeListener changeListener);
  
  public void setCreationValue(int value);

  public void setExperiencedValue(int value);
}