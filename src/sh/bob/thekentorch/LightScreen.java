package sh.bob.thekentorch;

import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.WindowManager;

public class LightScreen extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_screen);

        int brightnessMode = 0;
        try {
            brightnessMode = Settings.System.getInt(getContentResolver(), 
                    Settings.System.SCREEN_BRIGHTNESS_MODE);
        } catch (SettingNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (brightnessMode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
            Settings.System.putInt(getContentResolver(), 
                    Settings.System.SCREEN_BRIGHTNESS_MODE, 
                    Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        }

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.screenBrightness = 1.0F; // set 50% brightness
        getWindow().setAttributes(layoutParams);

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = pm.newWakeLock(
                PowerManager.FULL_WAKE_LOCK, "My wakelook");
        wakeLock.acquire();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_light_screen, menu);
        return true;
    }
}
