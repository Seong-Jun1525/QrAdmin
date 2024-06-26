package kr.ac.yuhan.cs.qradmin;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.yuhan.cs.qradmin.util.ChangeMode;
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.NeumorphImageView;

public class AdminFormActivity extends AppCompatActivity {
    private NeumorphImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_form);
        LinearLayout adminFormPage = findViewById(R.id.adminFormPage);

        // Receives current mode value
        int modeValue = getIntent().getIntExtra("mode", 1);

        // Receives background color value passed from MainActivity
        int backgroundColor = getIntent().getIntExtra("background_color", Color.rgb(236, 240, 243));

        // Setting BackgroundColor
        View backgroundView = getWindow().getDecorView().getRootView();
        backgroundView.setBackgroundColor(backgroundColor);

        // AdminForm Page CardView content
        NeumorphCardView adminAddCardView = findViewById(R.id.adminAddCardView);
        NeumorphCardView editTextIdField = findViewById(R.id.editTextIdField);
        NeumorphCardView editTextPwField = findViewById(R.id.editTextPwField);
        NeumorphCardView editTextPositionField = findViewById(R.id.editTextPositionField);
        NeumorphButton adminAddBtn = findViewById(R.id.adminAddBtn);

        // AdminForm Page Btn
        backBtn = findViewById(R.id.backBtn);

        if(modeValue == 1) {
            // DarkMode
            ChangeMode.applySubTheme(adminFormPage, modeValue);

            // AdminForm Page Btn
            ChangeMode.setColorFilterDark(backBtn);
            ChangeMode.setDarkShadowCardView(backBtn);

            // AdminForm Page CardView content
            ChangeMode.setDarkShadowCardView(adminAddCardView);
            ChangeMode.setDarkShadowCardView(editTextIdField);
            ChangeMode.setDarkShadowCardView(editTextPwField);
            ChangeMode.setDarkShadowCardView(editTextPositionField);
            ChangeMode.setDarkShadowCardView(adminAddBtn);
        }
        else {
            // LightMode
            adminAddBtn.setBackgroundColor(Color.rgb(0, 174, 142));
            ChangeMode.setLightShadowCardView(adminAddBtn);
        }

        // BackBtn onClickListener
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change ShapeType to 'pressed' when clicked
                backBtn.setShapeType(1);
                // After clicked, it changes back to 'flat'
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        backBtn.setShapeType(0);
                    }
                }, 200);
                finish();
            }
        });
    }
}
