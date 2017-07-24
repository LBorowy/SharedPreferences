package pl.lborowy.sharedpreferences;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.lborowy.sharedpreferences.preferences.TermsStatePreferences;

public class MainActivity extends AppCompatActivity {

    public static final String TERMS_ARE_ACCEPTED = "terms_are_accepted";
    @BindView(R.id.activity_main_basicView)
    View basicView;

    @BindColor(R.color.myRed)
    int redColor;

    @BindColor(R.color.myBlue)
    int blueColor;

    @BindColor(R.color.myGreen)
    int greenColor;

    @BindColor(R.color.myWhite)
    int whiteColor;

    @BindView(R.id.mainActivity_button_red)
    Button buttonRed;

    @BindView(R.id.mainActivity_button_blue)
    Button buttonBlue;

    @BindView(R.id.mainActivity_button_green)
    Button buttonGreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        checkTerms();
    }

    private void checkTerms() {
        if (!areTermsAccepted()) {
            showTermsDialog();
        }
        else {
            finish();
        }
    }

    private void showTermsDialog() {
        // akceptacja warunków
        new AlertDialog.Builder(this)
                .setCancelable(false) // klik poza dialog nie obejdzie go
                .setTitle("Regulamin")
                .setMessage("Akceptuj lub nie")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        storeTermsAccepted();
                        dialogInterface.dismiss();
                    }
                })
                .setNeutralButton("NIE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Nie to nie ;]", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .create()
                .show();
    }

    private void storeTermsAccepted() {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        preferences.edit().putBoolean(TERMS_ARE_ACCEPTED, true).apply();
        TermsStatePreferences.setTermsState(this,true);
    }

    private boolean areTermsAccepted() {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        return preferences.getBoolean(TERMS_ARE_ACCEPTED, false);
         return TermsStatePreferences.getTermsState(this);
    }

    @OnClick(R.id.mainActivity_button_red)
    public void clickRedButton() {
        setAndStoreColor(redColor);
    }

    @OnClick(R.id.mainActivity_button_blue)
    public void clickBlueButton() {
        setAndStoreColor(blueColor);
    }

    @OnClick(R.id.mainActivity_button_green)
    public void clickGreenButton() {
        setAndStoreColor(greenColor);
    }

    int currentColor;

    @Override
    protected void onResume() {
        super.onResume();
        //// TODO: 2017-07-24 load color
        saveColor();
    }

    private void saveColor() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        currentColor = sharedPreferences.getInt("background_color", whiteColor);
        setColor(currentColor);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //// TODO: 2017-07-24 or store color here
    }

    private void setAndStoreColor(int color) {
        setColor(color);
        storeColor();
    }

    private void setColor(int color) {
        currentColor = color;
        basicView.setBackgroundColor(color);
        //// TODO: 2017-07-24  store color here
    }

    private void storeColor() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("background_color", currentColor);
        editor.apply();
    }
}
