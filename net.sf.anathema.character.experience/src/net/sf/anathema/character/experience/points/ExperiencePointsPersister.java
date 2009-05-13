package net.sf.anathema.character.experience.points;

import static net.sf.anathema.character.experience.plugin.ExperiencePlugin.*;

import java.io.IOException;
import java.io.OutputStream;

import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;
import net.sf.anathema.basics.item.persistence.IPluginDocumentFactory;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.core.model.template.NullModelTemplate;
import net.sf.anathema.character.experience.IExperiencePoints;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Document;
import org.dom4j.Element;

public class ExperiencePointsPersister implements IModelPersister<NullModelTemplate, IExperiencePoints> {

  private static final String ATTRIB_COMMENT = "comment"; //$NON-NLS-1$
  private static final String ATTRIB_POINTS = "points"; //$NON-NLS-1$
  private static final String TAG_ENTRY = "entry"; //$NON-NLS-1$
  private static final String TAG_EXPERIENCE_POINTS = "experiencePoints"; //$NON-NLS-1$
  private final IPluginDocumentFactory documentFactory;

  public ExperiencePointsPersister() {
    this(new BundlePersistenceUtilities());
  }

  public ExperiencePointsPersister(IPluginDocumentFactory documentFactory) {
    this.documentFactory = documentFactory;
  }

  @Override
  public IExperiencePoints createNew(NullModelTemplate template) {
    return new ExperiencePoints();
  }

  @Override
  public IExperiencePoints load(Document document, NullModelTemplate template) throws PersistenceException {
    IExperiencePoints experiencePoints = createNew(template);
    for (Element entryElement : ElementUtilities.elements(document.getRootElement(), TAG_ENTRY)) {
      experiencePoints.add(loadEntry(entryElement));
    }
    return experiencePoints;
  }

  private ExperienceEntry loadEntry(Element entryElement) throws PersistenceException {
    ExperienceEntry entry = new ExperienceEntry();
    entry.points = ElementUtilities.getRequiredIntAttrib(entryElement, ATTRIB_POINTS);
    entry.comment = entryElement.attributeValue(ATTRIB_COMMENT);
    return entry;
  }

  @Override
  public void save(OutputStream stream, IExperiencePoints item) throws IOException, PersistenceException {
    Document document = documentFactory.createDocument(TAG_EXPERIENCE_POINTS, PLUGIN_ID);
    for (ExperienceEntry entry : item.getEntries()) {
      saveEntry(document.getRootElement(), entry);
    }
    DocumentUtilities.save(document, stream);
  }

  private void saveEntry(Element parent, ExperienceEntry entry) {
    Element entryElement = parent.addElement(TAG_ENTRY);
    entryElement.addAttribute(ATTRIB_POINTS, String.valueOf(entry.points));
    entryElement.addAttribute(ATTRIB_COMMENT, entry.comment);
  }
}