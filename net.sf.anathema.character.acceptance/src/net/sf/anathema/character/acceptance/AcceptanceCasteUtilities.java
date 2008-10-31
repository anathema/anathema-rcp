package net.sf.anathema.character.acceptance;

import java.io.ByteArrayInputStream;

import net.sf.anathema.character.caste.ICaste;
import net.sf.anathema.character.caste.ICasteModel;
import net.sf.anathema.character.core.character.CharacterId;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;

public class AcceptanceCasteUtilities {

  public static final void initPersistentCaste(IFolder folder, String casteId) throws CoreException {
    String casteXml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\r\n" //$NON-NLS-1$
        + "<casteModel bundleVersion=\"1.0.0\" caste=\"" //$NON-NLS-1$
        + casteId
        + "\"/>"; //$NON-NLS-1$
    folder.getFile("caste.model").create(new ByteArrayInputStream(casteXml.getBytes()), true, null); //$NON-NLS-1$
  }

  public static final void setCasteInModel(IFolder folder, String casteId) {
    ModelIdentifier casteIdentifier = new ModelIdentifier(new CharacterId(folder), ICasteModel.ID);
    ICasteModel casteModel = (ICasteModel) ModelCache.getInstance().getModel(casteIdentifier);
    for (ICaste caste : casteModel.getOptions()) {
      if (caste.getId().equals(casteId)) {
        casteModel.setCaste(caste);
      }
    }
  }
}