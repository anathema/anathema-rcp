package net.sf.anathema.character.experience.importwizard;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.experience.internal.Experience;
import net.sf.anathema.character.experience.internal.ExperiencePersister;
import net.sf.anathema.character.importwizard.IExecutableConverter;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Document;

public class ExperienceConverter extends AbstractExecutableExtension implements IExecutableConverter {

  private static final String TAG_EXPERIENCED = "experienced"; //$NON-NLS-1$
  private static final String TAG_STATISTICS = "Statistics"; //$NON-NLS-1$

  public Document convert(Document document) {
    boolean attribute = ElementUtilities.getBooleanAttribute(
        document.getRootElement().element(TAG_STATISTICS),
        TAG_EXPERIENCED,
        false);
    Experience experience = new Experience();
    experience.setExperienced(attribute);
    return new ExperiencePersister().createExperienceDocument(experience);
  }

}
