package mobi.huanyuan.spider.ui;

import de.felixroske.jfxsupport.SplashScreen;

public class CustomSplash extends SplashScreen {
    @Override
    public boolean visible() {
        return super.visible();
    }

    @Override
    public String getImagePath() {
        return "/images/banner.png";
    }
}
