package com.micropop.tedit.Libraryz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Environment;
import android.view.ViewGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Magnus on 25.10.2015.
 */
public class SaveLib {
    public static SaveLib.Composer context(Context context) {
        return new SaveLib.Composer(context);
    }

    public static class Composer {


        private Context context;


        public Composer(Context context) {
            this.context = context;

        }

        public void SaveImage(final ViewGroup vie) {
            File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/teditz");
            boolean success = true;
            if(!folder.exists()) {
                success = folder.mkdir();

            }
            if (success) {

            } else {

            }
            Calendar c = Calendar.getInstance();




            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(c.getTime());


            vie.setDrawingCacheEnabled(true);
            Bitmap bmp = Bitmap.createBitmap(vie.getDrawingCache());
            vie.setDrawingCacheEnabled(false);
            try {
                FileOutputStream fos = new FileOutputStream(new File(Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/teditz", "Tedit"
                        + "-" + formattedDate + "-" + System.currentTimeMillis() + ".png"));
                bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
