package net.sf.anathema.character.freebies.attributes.problems;

import java.util.List;

import net.sf.anathema.character.attributes.model.IAttributesPluginConstants;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.IModelChangeListener;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.attributes.FavoredAttributeCountHandler;
import net.sf.anathema.character.freebies.attributes.PrimaryAttributeFreebies;
import net.sf.anathema.character.freebies.attributes.SecondaryAttributeFreebies;
import net.sf.anathema.character.freebies.attributes.TertiaryAttributeFreebies;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;
import net.sf.anathema.character.freebies.problem.FreebiesProblemFactory;
import net.sf.anathema.character.freebies.problem.FreebiesProblemTemplate;
import net.sf.anathema.character.freebies.problem.HasUnspentFreebies;

public class AttributeFreebiesProblemFactory {

  private final ICreditManager creditManager = new CreditManager();
  private final IModelCollection modelCollection = ModelCache.getInstance();

  public void addAll(List<IModelChangeListener> modelListeners) {
    modelListeners.add(createForPrimaryFreebies());
    modelListeners.add(createForSecondaryFreebies());
    modelListeners.add(createForTertiaryFreebies());
    modelListeners.add(createFavoredCount());
  }

  public IModelChangeListener createForPrimaryFreebies() {
    IFreebiesHandler freebiesHandler = new PrimaryAttributeFreebies(modelCollection, creditManager);
    FreebiesProblemTemplate dto = createDto(freebiesHandler);
    dto.description = "There are still primary attribute dots available.";
    dto.errorMessage = "Error creating primary attribute dots problem marker.";
    dto.markerType = "net.sf.anathema.character.freebies.attributes.marker.primary"; //$NON-NLS-1$
    return new FreebiesProblemFactory(dto);
  }

  public IModelChangeListener createForSecondaryFreebies() {
    IFreebiesHandler freebiesHandler = new SecondaryAttributeFreebies(modelCollection, creditManager);
    FreebiesProblemTemplate dto = createDto(freebiesHandler);
    dto.description = "There are still secondary attribute dots available.";
    dto.errorMessage = "Error creating secondary attribute dots problem marker.";
    dto.markerType = "net.sf.anathema.character.freebies.attributes.marker.secondary"; //$NON-NLS-1$
    return new FreebiesProblemFactory(dto);
  }

  public IModelChangeListener createForTertiaryFreebies() {
    IFreebiesHandler freebiesHandler = new TertiaryAttributeFreebies(modelCollection, creditManager);
    FreebiesProblemTemplate dto = createDto(freebiesHandler);
    dto.description = "There are still tertiary attribute dots available.";
    dto.errorMessage = "Error creating tertiary attribute dots problem marker.";
    dto.markerType = "net.sf.anathema.character.freebies.attributes.marker.tertiary"; //$NON-NLS-1$
    return new FreebiesProblemFactory(dto);
  }

  public IModelChangeListener createFavoredCount() {
    IFreebiesHandler freebiesHandler = new FavoredAttributeCountHandler();
    FreebiesProblemTemplate dto = createDto(freebiesHandler);
    dto.description = "There are still favored attributes unchosen.";
    dto.errorMessage = "Error creating favored attributes count problem marker.";
    dto.markerType = "net.sf.anathema.character.freebies.attributes.marker.favoredcount"; //$NON-NLS-1$
    return new FreebiesProblemFactory(dto);
  }

  private FreebiesProblemTemplate createDto(IFreebiesHandler freebiesHandler) {
    FreebiesProblemTemplate dto = new FreebiesProblemTemplate();
    dto.hasProblem = new HasUnspentFreebies(creditManager, freebiesHandler);
    dto.editorOpener = "net.sf.anathema.character.attributes.editor"; //$NON-NLS-1$
    dto.modelName = "Attributes";
    dto.modelId = IAttributesPluginConstants.MODEL_ID;
    return dto;
  }
}