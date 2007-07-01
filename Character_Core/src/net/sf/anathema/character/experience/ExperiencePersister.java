package net.sf.anathema.character.experience;

import java.io.IOException;
import java.io.OutputStream;

import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class ExperiencePersister implements IModelPersister<IExperience> {

  private static final String TAG_MODEL = "model";
  private static final String ATTRIB_EXPERIENCED = "experienced"; //$NON-NLS-1$

  @Override
  public IExperience load(Document document) throws PersistenceException {
    IExperience experience = createNew();
    experience.setExperienced(ElementUtilities.getRequiredBooleanAttrib(document.getRootElement(), ATTRIB_EXPERIENCED));
    return experience;
  }

  @Override
  public void save(OutputStream stream, IExperience item) throws IOException, PersistenceException {
    Element root = DocumentHelper.createElement(TAG_MODEL);
    Document document = DocumentHelper.createDocument(root);
    ElementUtilities.addAttribute(root, ATTRIB_EXPERIENCED, item.isExperienced());
    DocumentUtilities.save(document, stream);
  }

  @Override
  public IExperience createNew() {
    return new Experience();
  }
}