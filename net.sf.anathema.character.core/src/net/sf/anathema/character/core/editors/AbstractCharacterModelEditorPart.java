package net.sf.anathema.character.core.editors;

import net.sf.anathema.basics.eclipse.resource.ResourceMarkerHandle;
import net.sf.anathema.basics.item.editor.AbstractPersistableItemEditorPart;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.resource.ResourceModelMarker;

import org.eclipse.core.resources.IResource;

public abstract class AbstractCharacterModelEditorPart<M extends IModel> extends AbstractPersistableItemEditorPart<M> {

  @Override
  protected Runnable createPostSaveRunnable() {
    final IResource modelResource = (IResource) getEditorInput().getAdapter(IResource.class);
    final Runnable runnable = super.createPostSaveRunnable();
    return new Runnable() {
      @Override
      public void run() {
        runnable.run();
        new ResourceModelMarker(null, new ResourceMarkerHandle(modelResource), new UneditedModelMarker()).markFile();
      }
    };
  }
}