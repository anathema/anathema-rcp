package net.sf.anathema.charms.xml.data;

import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.SourceDto;
import net.sf.anathema.charms.xml.BasicCharm;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class DatedCharm extends BasicCharm implements IDatedCharm {

  private static final String ATTRIB_TYPE = "type"; //$NON-NLS-1$
  private static final String TAG_CHARMTYPE = "charmtype"; //$NON-NLS-1$
  private static final String TAG_ATTRIBUTE = "attribute"; //$NON-NLS-1$
  private static final String TAG_VISUALIZE = "visualize"; //$NON-NLS-1$
  private static final String TAG_CHARM_ATTRIBUTE = "charmAttribute"; //$NON-NLS-1$
  private static final String TAG_SOURCE = "source"; //$NON-NLS-1$
  private static final String ATTRIB_SOURCE = "source"; //$NON-NLS-1$
  private static final String TAG_COST = "cost"; //$NON-NLS-1$

  public DatedCharm(Element charmElement) {
    super(charmElement);
  }

  @Override
  public CharmDto createDto() throws PersistenceException {
    CharmDto dto = new CharmDto();
    dto.type = charmElement.element(TAG_CHARMTYPE).attributeValue(ATTRIB_TYPE).toLowerCase();
    addKeywords(dto);
    addSources(dto);
    addCosts(dto);
    return dto;
  }

  private void addCosts(CharmDto dto) throws PersistenceException {
    Element costElement = charmElement.element(TAG_COST);
    if (costElement == null) {
      return;
    }
    new XmlCostReader(costElement, dto).read();
  }

  private void addKeywords(CharmDto dto) {
    for (Element keywordElement : ElementUtilities.elements(charmElement, TAG_CHARM_ATTRIBUTE)) {
      if (ElementUtilities.getBooleanAttribute(keywordElement, TAG_VISUALIZE, false)) {
        dto.keywords.add(keywordElement.attributeValue(TAG_ATTRIBUTE));
      }
    }
  }

  private void addSources(CharmDto dto) {
    for (Element source : ElementUtilities.elements(charmElement, TAG_SOURCE)) {
      SourceDto sourceDto = new SourceDto();
      sourceDto.source = source.attributeValue(ATTRIB_SOURCE);
      dto.sources.add(sourceDto);
    }
  }
}