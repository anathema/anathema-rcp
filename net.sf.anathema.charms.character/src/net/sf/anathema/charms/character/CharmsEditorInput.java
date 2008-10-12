package net.sf.anathema.charms.character;

import java.net.URL;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.model.AbstractCharacterModelEditorInput;

import org.eclipse.core.resources.IFile;

public class CharmsEditorInput extends AbstractCharacterModelEditorInput<ICharmModel> {

  private final ICharmModel charms;
  private String treeId;

  public CharmsEditorInput(IFile file, URL imageUrl, ICharmModel charms, IDisplayNameProvider displayNameProvider) {
    super(file, imageUrl, displayNameProvider, new CharmsPersister());
    this.charms = charms;
  }

  @Override
  protected String getModelId() {
    return ICharmModel.MODEL_ID;
  }

  @Override
  public ICharmModel getItem() {
    return charms;
  }

  public void setTreeId(String treeId) {
    this.treeId = treeId;
  }

  public String getTreeId() {
    return treeId;
  }

  @Override
  public String getName() {
    return treeId + " - " + super.getName(); //$NON-NLS-1$
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof CharmsEditorInput)) {
      return false;
    }
    return super.equals(obj) && ObjectUtilities.equals(((CharmsEditorInput) obj).treeId, treeId);
  }
}