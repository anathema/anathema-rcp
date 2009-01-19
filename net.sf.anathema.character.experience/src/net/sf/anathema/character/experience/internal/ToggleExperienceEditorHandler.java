package net.sf.anathema.character.experience.internal;

import java.util.Map;

import net.sf.anathema.basics.eclipse.ui.PartContainer;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.experience.IExperience;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.menus.UIElement;

public class ToggleExperienceEditorHandler extends AbstractToggleExperienceHandler {
  private final ExperienceListening listening = new ExperienceListening();

  @Override
  protected ModelIdentifier getExperienceIdentifier(ExecutionEvent event) throws ExecutionException {
    IEditorPart editor = HandlerUtil.getActiveEditorChecked(event);
    ModelIdentifier identifier = getExperienceIdentifier(editor);
    return identifier;
  }

  private ModelIdentifier getExperienceIdentifier(IEditorPart editor) throws ExecutionException {
    IModelIdentifier currentIdentifier = (IModelIdentifier) editor.getEditorInput().getAdapter(IModelIdentifier.class);
    if (currentIdentifier == null) {
      throw new ExecutionException(
          "This handler should not be active when the active editor is not a character editor.");//$NON-NLS-1$
    }
    ModelIdentifier identifier = new ModelIdentifier(currentIdentifier.getCharacterId(), IExperience.MODEL_ID);
    return identifier;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void updateElement(final UIElement element, Map parameters) {
    listening.reset();
    IWorkbenchWindow window = (IWorkbenchWindow) parameters.get("org.eclipse.ui.IWorkbenchWindow"); //$NON-NLS-1$
    PartContainer partContainer = new PartContainer(window);
    IEditorInput input = partContainer.getEditorInput();
    IModelIdentifier modelIdentifier = (IModelIdentifier) input.getAdapter(IModelIdentifier.class);
    if (modelIdentifier == null) {
      return;
    }
    IExperience experience = (IExperience) ModelCache.getInstance().getModel(
        new ModelIdentifier(modelIdentifier.getCharacterId(), IExperience.MODEL_ID));
    listening.init(window, experience);
    element.setChecked(experience.isExperienced());
  }
}