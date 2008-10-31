package net.sf.anathema.character.core.traitview;

interface IIntValuePainter {

  public void init(IRedrawable redawable, int imageWidth, int imageHeight);
  
  public boolean isResponsible(IIntValuePaintContext context, int index);
  
  public void drawImage(IIntValuePaintContext context, int index);
}