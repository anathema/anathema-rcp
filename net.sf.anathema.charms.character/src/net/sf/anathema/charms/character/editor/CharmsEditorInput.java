package net.sf.anathema.charms.character.editor;

import java.net.URL;

import net.disy.commons.core.provider.IProvider;
import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.basics.repository.linkage.util.ILink;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.model.AbstractCharacterModelEditorInput;
import net.sf.anathema.charms.character.model.CharmsPersister;
import net.sf.anathema.charms.character.model.ICharmModel;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IPersistableElement;

public class CharmsEditorInput extends AbstractCharacterModelEditorInput<ICharmModel> {

  public static final String PERSISTABLE_FACTORY_ID = "net.sf.anathema.charms.character.model.persistablefactory"; //$NON-NLS-1$
  public static final String MEMENTO_TREE_ID = "treeId"; //$NON-NLS-1$
  private final ICharmModel charms;
  private String treeId;

  public CharmsEditorInput(IFile file, URL imageUrl, ICharmModel charms, IDisplayNameProvider displayNameProvider) {
    super(file, imageUrl, displayNameProvider, new CharmsPersister());
    this.charms = charms;
    final ILink originalLink = (ILink) getAdapter(ILink.class);
    set(ILink.class, new IProvider<ILink>() {
      @Override
      public ILink getObject() {
        return new StringLinkDecorator(originalLink, new IProvider<String>() {

          @Override
          public String getObject() {
            return getTreeId();
          }
        });
      }
    });
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
    if (treeId != null) {
      return treeId + " - " + super.getName(); //$NON-NLS-1$
    }
    return super.getName();
  }

  @Override
  public final IPersistableElement getPersistable() {
    return new TreeIdPersistableDecoration(super.getPersistable(), getTreeId());
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof CharmsEditorInput)) {
      return false;
    }
    return super.equals(obj) && ObjectUtilities.equals(((CharmsEditorInput) obj).treeId, treeId);
  }
}