package net.sf.anathema.campaign.conflictweb.parts;

import net.sf.anathema.campaign.conflictweb.model.ConflictWeb;
import net.sf.anathema.campaign.conflictweb.model.IParty;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

public class ConflictWebEditpartFactory implements EditPartFactory {

  @Override
  public EditPart createEditPart(EditPart context, Object model) {
    if (model instanceof IParty) {
      PartyEditPart partyEditPart = new PartyEditPart();
      partyEditPart.setModel(model);
      return partyEditPart;
    }
    else if (model instanceof ConflictWeb) {
      ConflictWebEditPart editPart = new ConflictWebEditPart();
      editPart.setModel(model);
      return editPart;
    }
    throw new UnsupportedOperationException("Unsupported model type " + model.getClass().getName());
  }
}