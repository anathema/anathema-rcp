package net.sf.anathema.character.experience.points;

import static net.sf.anathema.character.experience.plugin.ExperiencePlugin.*;

import java.io.IOException;
import java.io.OutputStream;

import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.core.model.template.NullModelTemplate;
import net.sf.anathema.character.experience.IExperiencePoints;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;

public class ExperiencePointsPersister implements IModelPersister<NullModelTemplate, IExperiencePoints> {

  private static final String TAG_EXPERIENCE_POINTS = "experiencePoints"; //$NON-NLS-1$

  @Override
  public IExperiencePoints createNew(NullModelTemplate template) {
    return new ExperiencePoints();
  }

  @Override
  public IExperiencePoints load(Document document, NullModelTemplate template) throws PersistenceException {
    return new ExperiencePoints();
  }

  @Override
  public void save(OutputStream stream, IExperiencePoints item) throws IOException, PersistenceException {
    BundlePersistenceUtilities bundleUtilities = new BundlePersistenceUtilities();
    Document document = bundleUtilities.createVersionedDocument(TAG_EXPERIENCE_POINTS, PLUGIN_ID);
    DocumentUtilities.save(document, stream);
  }
}