package net.sf.anathema.character.description;

import net.sf.anathema.basics.item.IPersistableEditorInput;
import net.sf.anathema.basics.item.editor.AbstractPersistableItemEditorPart;
import net.sf.anathema.basics.item.editor.IPersistableItemEditor;
import net.sf.anathema.basics.item.editor.UpdatePartNameListener;
import net.sf.anathema.basics.jface.text.SimpleTextView;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.textualdescription.ITextView;
import net.sf.anathema.lib.textualdescription.ITextualDescription;
import net.sf.anathema.lib.textualdescription.TextualPresenter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

public class CharacterDescriptionEditor extends AbstractPersistableItemEditorPart implements
    IPersistableItemEditor {

  public static final String EDITOR_ID = "net.sf.anathema.character.description.editor"; //$NON-NLS-1$
  private ICharacterDescription characterDescription;
  private ITextView nameView;

  @Override
  @SuppressWarnings("unchecked")
  public IPersistableEditorInput<ICharacterDescription> getEditorInput() {
    return (IPersistableEditorInput<ICharacterDescription>) super.getEditorInput();
  }

  @Override
  public void init(IEditorSite site, IEditorInput input) throws PartInitException {
    try {
      setInput(input);
      IPersistableEditorInput<ICharacterDescription> itemInput = getEditorInput();
      characterDescription = itemInput.loadItem();
      getItem().addDirtyListener(new IChangeListener() {
        public void changeOccured() {
          firePropertyChange(PROP_DIRTY);
        }
      });
      setSite(site);
      setTitleImage(itemInput.getImageDescriptor().createImage());
      getItem().getName().addTextChangedListener(new UpdatePartNameListener(this));
      setPartName(getEditorInput().getName());
    }
    catch (Exception e) {
      throw new PartInitException("Error initializing styled text editor.", e); //$NON-NLS-1$
    }
  }


  @Override
  protected ICharacterDescription getItem() {
    return characterDescription;
  }

  @Override
  public void createPartControl(Composite parent) {
    parent.setLayout(new GridLayout(2, false));
    Label nameLabel = new Label(parent, SWT.LEFT);
    nameLabel.setText("Name:");
    nameLabel.setLayoutData(createLabelData());
    nameView = SimpleTextView.createSingleLineView(parent);
    final ITextualDescription nameModel = getItem().getName();
    new TextualPresenter(nameView, nameModel).initPresentation();
    Label contentLabel = new Label(parent, SWT.LEFT);
    contentLabel.setText("Physical Description:");
    contentLabel.setLayoutData(createLabelData());
    final ITextualDescription contentDescription = getItem().getPhysicalDescription();
    ITextView physicalDescriptionView = SimpleTextView.createMultiLineView(parent);
    new TextualPresenter(physicalDescriptionView, contentDescription).initPresentation();
  }

  protected GridData createLabelData() {
    return new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false);
  }

  @Override
  public void setFocus() {
    nameView.setFocus();
  }
}