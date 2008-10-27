package net.sf.anathema.character.core.fake;

import net.sf.anathema.basics.eclipse.resource.fake.ResourceObjectMother;
import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.IPersistableEditorInput;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.character.ModelIdentifier;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;

public class CharacterObjectMother {

  public static IEditorInput createCharacterEditorInput(IModelIdentifier modelIdentifier) {
    IEditorInput editorInput = EasyMock.createNiceMock(IEditorInput.class);
    EasyMock.expect(editorInput.getAdapter(IModelIdentifier.class)).andReturn(modelIdentifier).anyTimes();
    EasyMock.replay(editorInput);
    return editorInput;
  }

  public static IEditorInput createCharacterEditorInput(ICharacterId characterIdentifier) {
    return createCharacterEditorInput(new ModelIdentifier(characterIdentifier, "fake")); //$NON-NLS-1$
  }

  public static IEditorInput createNonCharacterEditorInput() {
    return createCharacterEditorInput((IModelIdentifier) null);
  }

  public static IPartContainer createPartContainerWithActiveEditorInput(IEditorInput editedInput) {
    IPartContainer partContainer = EasyMock.createNiceMock(IPartContainer.class);
    EasyMock.expect(partContainer.getEditorInput()).andReturn(editedInput).anyTimes();
    EasyMock.replay(partContainer);
    return partContainer;
  }

  public static IModelCollection createModelProvider(IModelIdentifier identifier, IModel model) {
    IModelCollection modelProvider = EasyMock.createNiceMock(IModelCollection.class);
    EasyMock.expect(modelProvider.getModel(identifier)).andStubReturn(model);
    EasyMock.expect(modelProvider.contains(identifier)).andStubReturn(true);
    EasyMock.replay(modelProvider);
    return modelProvider;
  }

  public static IModelCollection createNonLoadingEmptyModelProvider() {
    Throwable expection = new IllegalStateException("Demand for non-existing model"); //$NON-NLS-1$
    IModelCollection modelProvider = EasyMock.createNiceMock(IModelCollection.class);
    EasyMock.expect(modelProvider.contains(EasyMock.isA(IModelIdentifier.class))).andStubReturn(false);
    EasyMock.expect(modelProvider.getModel(EasyMock.isA(IModelIdentifier.class))).andStubThrow(expection);
    EasyMock.replay(modelProvider);
    return modelProvider;
  }

  public static IFile createFileWithBonusPointMarkers(IMarker[] markers) throws CoreException {
    IFile file = EasyMock.createNiceMock(IFile.class);
    EasyMock.expect(file.exists()).andStubReturn(true);
    String markerType = "net.sf.anathema.markers.bonuspoints"; //$NON-NLS-1$
    EasyMock.expect(file.findMarkers(markerType, false, IResource.DEPTH_ZERO)).andStubReturn(markers);
    EasyMock.expect(file.createMarker(EasyMock.isA(String.class))).andStubReturn(ResourceObjectMother.createMarker());
    EasyMock.replay(file);
    return file;
  }

  public static IPersistableEditorInput< ? > createPersistableEditorInputFor(IItem item) {
    IPersistableEditorInput< ? > input = EasyMock.createMock(IPersistableEditorInput.class);
    EasyMock.expect(input.getItem()).andStubReturn(item);
    EasyMock.expect(input.getName()).andStubReturn("name"); //$NON-NLS-1$
    EasyMock.expect(input.getImageDescriptor()).andStubReturn(ImageDescriptor.getMissingImageDescriptor());
    EasyMock.replay(input);
    return input;
  }

  public static IMarker createBonusPointMarker(String handlerType, int bonusPoints) throws CoreException {
    IMarker marker = EasyMock.createNiceMock(IMarker.class);
    EasyMock.expect(marker.getAttribute("bonusPoints")).andStubReturn(bonusPoints); //$NON-NLS-1$
    EasyMock.expect(marker.getAttribute("handlerType")).andStubReturn(handlerType); //$NON-NLS-1$
    EasyMock.replay(marker);
    return marker;
  }
}