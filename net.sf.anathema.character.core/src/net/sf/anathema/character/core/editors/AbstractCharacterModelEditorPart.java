package net.sf.anathema.character.core.editors;

import net.sf.anathema.basics.eclipse.resource.ResourceMarkerHandle;
import net.sf.anathema.basics.item.editor.AbstractPersistableItemEditorPart;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.resource.ResourceModelMarker;

import org.eclipse.core.resources.IResource;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

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

  protected final ICharacterId getCharacterId() {
    return getModelIdentifier().getCharacterId();
  }

  protected final IModelIdentifier getModelIdentifier() {
    return (IModelIdentifier) getEditorInput().getAdapter(IModelIdentifier.class);
  }

  protected final Composite createFormBody(Composite parent, FormToolkit toolkit) {
    Form form = toolkit.createForm(parent);
    toolkit.decorateFormHeading(form);
    form.setText(getPersistableEditorInput().getName());
    Composite body = form.getBody();
    body.setLayout(new GridLayout(1, false));
    return body;
  }
}