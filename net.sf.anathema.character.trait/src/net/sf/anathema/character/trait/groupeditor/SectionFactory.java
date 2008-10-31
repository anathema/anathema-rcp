package net.sf.anathema.character.trait.groupeditor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

public class SectionFactory {

  private final FormToolkit toolkit;

  public SectionFactory(FormToolkit toolkit) {
    this.toolkit = toolkit;
  }

  public Composite create(final Composite container, String title) {
    Section section = toolkit.createSection(container, ExpandableComposite.TITLE_BAR);
    section.setText(title);
    Composite sectionClient = toolkit.createComposite(section);
    section.setClient(sectionClient);
    return sectionClient;
  }

  public Composite create(final Composite container, String title, String description) {
    Section section = toolkit.createSection(container, Section.DESCRIPTION
        | ExpandableComposite.TITLE_BAR
        | ExpandableComposite.TWISTIE
        | ExpandableComposite.EXPANDED);
    section.setDescription(description);
    section.setText(title);
    Composite sectionClient = toolkit.createComposite(section);
    section.setClient(sectionClient);
    return sectionClient;
  }
}
