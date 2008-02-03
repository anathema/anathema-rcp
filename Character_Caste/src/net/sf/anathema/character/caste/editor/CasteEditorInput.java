package net.sf.anathema.character.caste.editor;

import java.net.URL;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.caste.model.ICasteModel;
import net.sf.anathema.character.caste.persistence.CasteModelPersister;
import net.sf.anathema.character.core.model.AbstractCharacterModelEditorInput;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.lib.control.change.ChangeControl;

import org.eclipse.core.resources.IFile;

public class CasteEditorInput extends AbstractCharacterModelEditorInput<ICasteModel> {

  private final ChangeControl control = new ChangeControl();
  private final ICasteModel casteModel;
  private final IExperience experience;

  public CasteEditorInput(
      IFile file,
      URL imageUrl,
      IDisplayNameProvider displayNameProvider,
      ICasteModel casteModel,
      IExperience experience) {
    super(file, imageUrl, displayNameProvider, new CasteModelPersister());
    this.casteModel = casteModel;
    this.experience = experience;
    experience.addChangeListener(new IChangeListener() {
      @Override
      public void stateChanged() {
        control.fireChangedEvent();
      }
    });
  }

  public void addModifiableListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  public void removeModifiableListener(IChangeListener listener) {
    control.removeChangeListener(listener);
  }

  @Override
  public ICasteModel getItem() {
    return casteModel;
  }

  @Override
  protected String getModelId() {
    return ICasteModel.ID;
  }

  public boolean isModifiable() {
    return !experience.isExperienced();
  }
}