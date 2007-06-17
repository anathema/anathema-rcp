package net.sf.anathema.character.trait.favorization;

public interface IFavorableStateVisitor {

  public void visitDefault(FavorableState state);

  public void visitFavored(FavorableState state);

  public void visitCaste(FavorableState state);
}