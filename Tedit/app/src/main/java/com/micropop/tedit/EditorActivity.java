package com.micropop.tedit;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.commit451.nativestackblur.NativeStackBlur;
import com.micropop.tedit.Libraryz.SaveLib;

import jp.wasabeef.blurry.Blurry;
import yuku.ambilwarna.AmbilWarnaDialog;

public class EditorActivity extends AppCompatActivity {
    int color = 0xff000000;

public ViewGroup imagge;
    public ImageView ImageView;
    public ViewGroup ViewGruop;
    public ToggleButton Textbutton;
    public HorizontalScrollView textToolz;
    public Spinner spinner;
    public EditText mTxt;
    public TextView Texti;
    public ToggleButton blurOF;
    public ToggleButton filtrz;
    public HorizontalScrollView filtersT;
    public RelativeLayout BackgroundRell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
imagge = (ViewGroup) findViewById(R.id.Imagge);
        filtersT = (HorizontalScrollView) findViewById(R.id.filters);
        filtersT.setVisibility(View.INVISIBLE);
        filtrz = (ToggleButton) findViewById(R.id.Filtirz);
        filtrz.setChecked(false);
        filtrz.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filtersT.setVisibility(View.VISIBLE);
                    filtrz.setBackgroundColor(getResources().getColor(R.color.colorAccentA400));
                    filtrz.setTextColor(getResources().getColor(R.color.colorAccentA100));
                } else {
                    filtersT.setVisibility(View.INVISIBLE);
                    filtrz.setBackgroundColor(getResources().getColor(R.color.colorAccentA100));
                    filtrz.setTextColor(getResources().getColor(R.color.colorAccentA400));
                }
            }
        });
        ImageView = (ImageView) findViewById(R.id.theImage);
        ViewGruop = (ViewGroup) findViewById(R.id.saveViewGroup);
        Texti = (TextView) findViewById(R.id.TextVieww);
        Typeface trebu = Typeface.createFromAsset(getAssets(), "fonts/trebuc.ttf");
        Texti.setTypeface(trebu);
        textToolz = (HorizontalScrollView) findViewById(R.id.TextTooolz);
        Textbutton = (ToggleButton) findViewById(R.id.TextToolz);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.fonts_array, R.layout.custo_spinner);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                parent.getItemAtPosition(pos);
                Spinner spinner = (Spinner) parent;
                if (spinner.getId() == R.id.spinner) {
                    switch (pos) {
                        case 0:
                            Typeface billa = Typeface.createFromAsset(getAssets(), "fonts/billabong.ttf");
                            Texti.setTypeface(billa);
                            break;
                        case 1:
                            Typeface impa = Typeface.createFromAsset(getAssets(), "fonts/impact.ttf");
                            Texti.setTypeface(impa);
                            break;
                        case 2:
                            Typeface lith = Typeface.createFromAsset(getAssets(), "fonts/LithosPro-Regular.otf");
                            Texti.setTypeface(lith);
                            break;
                        case 3:
                            Typeface treb = Typeface.createFromAsset(getAssets(), "fonts/trebuc.ttf");
                            Texti.setTypeface(treb);
                            break;
                        case 4:
                            Typeface time = Typeface.createFromAsset(getAssets(), "fonts/times.ttf");
                            Texti.setTypeface(time);
                            break;

                    }
                }
            }


            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        blurOF = (ToggleButton) findViewById(R.id.button3);
        blurOF.setChecked(false);
        blurOF.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_blur_off_white_36dp));
        blurOF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    blurOF.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_blur_on_white_36dp));
                    Blurry.with(EditorActivity.this)
                            .radius(10)
                            .sampling(9)
                            .async()
                            .animate(0)
                            .onto(imagge);


                } else {
                    blurOF.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_blur_off_white_36dp));
                    Blurry.delete(imagge);
                }
            }
        });



    textToolz.setVisibility(View.INVISIBLE);
        Textbutton.setChecked(false);

        Textbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textToolz.setVisibility(View.VISIBLE);
                    Textbutton.setBackgroundColor(getResources().getColor(R.color.colorAccentA400));
                    Textbutton.setTextColor(getResources().getColor(R.color.colorAccentA100));
                }
                else {
                    textToolz.setVisibility(View.INVISIBLE);
                    Textbutton.setBackgroundColor(getResources().getColor(R.color.colorAccentA100));
                    Textbutton.setTextColor(getResources().getColor(R.color.colorAccentA400));
            }
        }
    });
        mTxt = (EditText) findViewById(R.id.inputText);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override


            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {



                    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {


                    Texti.setText(mTxt.getText());
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mTxt.getWindowToken(), 0);
                    return false;
                }

                return true;
            }


        });
        ImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder ChooserD = new AlertDialog.Builder(EditorActivity.this)
                        .setMessage(getResources().getString(R.string.chooserD))
                        .setTitle(getResources().getString(R.string.chooserT))
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton(R.string.camera, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                                startActivityForResult(cameraIntent, 16);
                            }
                        })
                        .setNeutralButton(R.string.gallery, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                                photoPickerIntent.setType("image/*");
                                startActivityForResult(photoPickerIntent, 10);
                            }
                        });
                ChooserD.show();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            SaveLib.context(this).SaveImage(ViewGruop);
            Snackbar.make(findViewById(android.R.id.content), R.string.saved, Snackbar.LENGTH_LONG)
                    .show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == 16) {

            Bitmap image = (Bitmap) data.getExtras().get("data");
            if (image == null) {
                ImageView.setBackgroundResource(R.drawable.ic_broken_image_white_48dp);
            } else {

                ImageView.setImageBitmap(image);
            }
        }
        if (resultCode == Activity.RESULT_OK && requestCode == 10) {
            Uri selectedImageUri = data.getData();
            String[] projection = { MediaStore.MediaColumns.DATA };
            Cursor cursor = managedQuery(selectedImageUri, projection, null, null,
                    null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            cursor.moveToFirst();
            String selectedImagePath = cursor.getString(column_index);
            Bitmap bm;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(selectedImagePath, options);
            final int REQUIRED_SIZE = 200;
            int scale = 1;
            while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                    && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                scale *= 2;
            options.inSampleSize = scale;
            options.inJustDecodeBounds = false;
            bm = BitmapFactory.decodeFile(selectedImagePath, options);

            ImageView.setImageBitmap(bm);

        }
    }
    public void colorPicker(View view) {
        AmbilWarnaDialog dialog = new AmbilWarnaDialog(EditorActivity.this, color, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {

                EditorActivity.this.color = color;
                Texti.setTextColor(color);

            }

            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }
        });
        dialog.show();
    }
    public void longlick(View view) {
        Toast.makeText(this, R.string.longlick, Toast.LENGTH_LONG).show();
    }



}
