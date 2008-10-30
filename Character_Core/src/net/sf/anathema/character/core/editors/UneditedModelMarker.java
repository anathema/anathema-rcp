package net.sf.anathema.character.core.editors;

import net.sf.anathema.character.core.problem.Messages;
import net.sf.anathema.character.core.resource.IModelMarker;

import org.eclipse.core.resources.IMarker;
import org.eclipse.osgi.util.NLS;

public class UneditedModelMarker implements IModelMarker {


  public static final String MARKER_TYPE = "net.sf.anathema.character.uneditedmodel.marker"; //$NON-NLS-1$


  @Override
  public String getDescription(String modelName, String characterName) {
    return NLS.bind(Messages.UneditedModelProblem_Description, new Object[] { modelName, characterName });
  }

  @Override
  public String getMarkerId() {
    return MARKER_TYPE;
  }

  @Override
  public boolean isActive(IMarker[] markers) {
    return markers.length == 0;
  }
}