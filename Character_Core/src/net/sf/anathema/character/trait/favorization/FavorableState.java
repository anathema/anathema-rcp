package net.sf.anathema.character.trait.favorization;

import net.sf.anathema.lib.util.IIdentificate;

public enum FavorableState implements IIdentificate {

  Default {
    @Override
    public void accept(IFavorableStateVisitor visitor) {
      visitor.visitDefault(this);
    }
  },

  Favored {
    @Override
    public void accept(IFavorableStateVisitor visitor) {
      visitor.visitFavored(this);
    }
  },

  Caste {
    @Override
    public void accept(IFavorableStateVisitor visitor) {
      visitor.visitCaste(this);
    }
  };
  @Override
  public String toString() {
    return getId();
  }

  public String getId() {
    return name();
  }

  public abstract void accept(IFavorableStateVisitor visitor);
}