package net.sf.anathema.character.experience.internal;

import java.io.IOException;
import java.io.OutputStream;

import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;
import net.sf.anathema.basics.item.persistence.BundleVersionCollection;
import net.sf.anathema.basics.item.persistence.IBundleVersionCollection;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.core.model.template.NullModelTemplate;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.experience.plugin.ExperiencePlugin;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Document;

public class ExperiencePersister implements IModelPersister<NullModelTemplate, IExperience> {

  private static final String TAG_MODEL = "model"; //$NON-NLS-1$
  private static final String ATTRIB_EXPERIENCED = "experienced"; //$NON-NLS-1$
  private final IBundleVersionCollection collection;

  public ExperiencePersister() {
    this(new BundleVersionCollection());
  }

  public ExperiencePersister(IBundleVersionCollection collection) {
    this.collection = collection;
  }

  @Override
  public IExperience load(Document document, NullModelTemplate template) throws PersistenceException {
    IExperience experience = createNew(template);
    experience.setExperienced(ElementUtilities.getRequiredBooleanAttrib(document.getRootElement(), ATTRIB_EXPERIENCED));
    return experience;
  }

  @Override
  public void save(OutputStream stream, IExperience item) throws IOException, PersistenceException {
    Document document = createExperienceDocument(item);
    DocumentUtilities.save(document, stream);
  }

  public Document createExperienceDocument(IExperience item) {
    Document document = new BundlePersistenceUtilities(collection).createDocument(
        TAG_MODEL,
        ExperiencePlugin.PLUGIN_ID);
    ElementUtilities.addAttribute(document.getRootElement(), ATTRIB_EXPERIENCED, item.isExperienced());
    return document;
  }

  @Override
  public IExperience createNew(NullModelTemplate template) {
    return new Experience();
  }
}