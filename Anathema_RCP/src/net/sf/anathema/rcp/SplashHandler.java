package net.sf.anathema.rcp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.splash.BasicSplashHandler;

public class SplashHandler extends BasicSplashHandler {

  @Override
  public void init(Shell splash) {
    super.init(splash);
    Rectangle progressRect = new Rectangle(295, splash.getSize().y - 18, splash.getSize().x - 298, 16);
    setProgressRect(progressRect);

    Rectangle messageRect = new Rectangle(95, splash.getSize().y - 18, 200, 16);
    setMessageRect(messageRect);

    setForeground(new RGB(255, 255, 255));

    getContent().addPaintListener(new PaintListener() {
      public void paintControl(PaintEvent e) {
        GC context = e.gc;
        context.setForeground(getForeground());
        context.setTextAntialias(SWT.ON);
        FontData[] fontdata = context.getFont().getFontData();
        for (FontData data : fontdata) {
          data.setStyle(SWT.BOLD);
          data.setHeight(data.getHeight() + 2);
        }
        context.setFont(new Font(e.display, fontdata));
        context.drawText("Rich Client", 430, 53, true);
      }
    });
  }
}