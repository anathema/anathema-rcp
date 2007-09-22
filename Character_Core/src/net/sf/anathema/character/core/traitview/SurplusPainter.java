package net.sf.anathema.character.core.traitview;

import net.disy.commons.core.util.Ensure;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

public final class SurplusPainter implements IIntValuePainter {
  private final Image surplusImage;
  private boolean showSurplus;
  private int surplusValue;
  private IRedrawable redrawable = new NullRedrawable();

  public SurplusPainter(Image surplusImage) {
    this.surplusImage = surplusImage;
  }

  public void setShowSurplus(boolean showSurplus) {
    this.showSurplus = showSurplus;
    redrawable.redraw();
  }

  public void setSurplusThreshold(int surplusValue) {
    if (this.surplusValue == surplusValue) {
      return;
    }
    this.surplusValue = surplusValue;
    redrawable.redraw();
  }

  @Override
  public void drawImage(IIntValuePaintContext context, int index) {
    context.drawImage(index, surplusImage);
  }

  @Override
  public boolean isResponsible(IIntValuePaintContext context, int index) {
    return showSurplus && surplusValue <= index && index < context.getValue();
  }

  @Override
  public void init(IRedrawable newRedrawable, int imageWidth, int imageHeight) {
    ImageData surplusData = surplusImage.getImageData();
    Ensure.ensureArgumentEquals(imageWidth, surplusData.width);
    Ensure.ensureArgumentEquals(imageHeight, surplusData.height);
    this.redrawable = newRedrawable;
  }
}