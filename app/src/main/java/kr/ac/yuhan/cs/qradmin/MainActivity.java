package kr.ac.yuhan.cs.qradmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.Date;

import kr.ac.yuhan.cs.qradmin.adapter.MemberAdapter;
import kr.ac.yuhan.cs.qradmin.data.MemberData;
import kr.ac.yuhan.cs.qradmin.util.ChangeMode;
import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.NeumorphImageView;

public class MainActivity extends AppCompatActivity {
    // Current Mode Value
    private int mode = 0;

    // MainActivity CardView
    private NeumorphCardView mainCardView;
    private NeumorphCardView footer_menu;

    // Footer Bar Menu
    private NeumorphImageView homeBtn;
    private NeumorphImageView memberBtn;
    private NeumorphImageView productBtn;
    private NeumorphImageView payHistoryBtn;
    private NeumorphImageView productPushBtn;

    // Member Menu
    private NeumorphCardView input_searchId;
    private NeumorphButton memberSearchBtn;
    private NeumorphCardView memberListCardView;

    // HOME Menu
    private NeumorphCardView adminBtn;
    private NeumorphCardView adminScheduleBtn;
    private NeumorphCardView callBtn;
    private NeumorphCardView login;

    // HOME Menu Image
    private ImageView adminSchedule;
    private ImageView adminList;
    private ImageView adminLogin;
    private ImageView call;

    // PaymentList Menu
    private NeumorphButton paySearchBtn;
    private NeumorphCardView payListCardView;
    private NeumorphCardView input_searchIdPay;

    // ProductRegister Menu
    private NeumorphCardView input_productImage;
    private NeumorphCardView input_productName;
    private NeumorphCardView input_productQuantity;
    private NeumorphCardView input_productCategory;
    private NeumorphCardView input_productPrice;
    private NeumorphButton createQRBtn;
    private NeumorphButton createProductBtn;

    // Header ButtonImage
    private NeumorphImageView setting;
    private NeumorphImageView changeMode;

    // Basic BackgroundColor
    private int backgroundColor;
    private int mainBackgroundColor = Color.rgb(236, 240, 243);
    private final int darkModeBackgroundColor = Color.rgb(97, 97, 97);
    private final int btnColor = Color.rgb(0, 174, 142);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Listview Setting
        ListView listView = findViewById(R.id.listView);

        // Create Fake Data
        ArrayList<MemberData> fakeDataList = createFakeData();

        // MemberAdapter Setting
        MemberAdapter adapter = new MemberAdapter(this, fakeDataList);
        listView.setAdapter(adapter);

        // ViewFlipper Setting
        final ViewFlipper vFlipper;
        vFlipper = (ViewFlipper) findViewById(R.id.viewFlipper1);

        // Main Layout Setting
        LinearLayout main = findViewById(R.id.main);

        // Basic Background Color Setting
        backgroundColor = mainBackgroundColor;
        main.setBackgroundColor(backgroundColor);

        Drawable backgroundDrawable = main.getBackground();
        mainBackgroundColor = ((ColorDrawable) backgroundDrawable).getColor();

        // MainActivity Header Id
        setting = (NeumorphImageView) findViewById(R.id.setting);
        changeMode = (NeumorphImageView) findViewById(R.id.darkMode);

        // Admin MainPage ImageView Id
        adminList = (ImageView) findViewById(R.id.adminList);
        call = (ImageView) findViewById(R.id.call);
        adminLogin = (ImageView) findViewById(R.id.adminLogin);
        adminSchedule = (ImageView) findViewById(R.id.adminSchedule);

        // MainActivity CardView & Footer Id
        mainCardView = (NeumorphCardView) findViewById(R.id.mainCardView);
        footer_menu = (NeumorphCardView) findViewById(R.id.footer_menu);

        // User Management Page Id
        input_searchId = (NeumorphCardView) findViewById(R.id.input_searchId);
        memberSearchBtn = (NeumorphButton) findViewById(R.id.memberSearchBtn);
        memberListCardView = (NeumorphCardView) findViewById(R.id.memberListCardView);

        // Payment List Id
        paySearchBtn = (NeumorphButton) findViewById(R.id.paySearchBtn);
        payListCardView = (NeumorphCardView) findViewById(R.id.payListCardView);
        input_searchIdPay =(NeumorphCardView) findViewById(R.id.input_searchIdPay);

        // Product Register Page Id
        input_productImage = (NeumorphCardView) findViewById(R.id.input_productImage);
        input_productName = (NeumorphCardView) findViewById(R.id.input_productName);
        input_productQuantity = (NeumorphCardView) findViewById(R.id.input_productQuantity);
        input_productCategory = (NeumorphCardView) findViewById(R.id.input_productCategory);
        input_productPrice = (NeumorphCardView) findViewById(R.id.input_productPrice);
        createQRBtn = (NeumorphButton) findViewById(R.id.createQRBtn);
        createProductBtn = (NeumorphButton) findViewById(R.id.createProductBtn);
        memberSearchBtn = (NeumorphButton) findViewById(R.id.memberSearchBtn);

        // Footer Menu Icon Id
        memberBtn = (NeumorphImageView) findViewById(R.id.memberBtn);
        productBtn = (NeumorphImageView) findViewById(R.id.productBtn);
        homeBtn = (NeumorphImageView) findViewById(R.id.homeBtn);
        payHistoryBtn = (NeumorphImageView) findViewById(R.id.payHistoryBtn);
        productPushBtn = (NeumorphImageView) findViewById(R.id.productPushBtn);

        // MainActivity Button BackgroundColor Setting
        createQRBtn.setBackgroundColor(btnColor);
        createProductBtn.setBackgroundColor(btnColor);
        memberSearchBtn.setBackgroundColor(btnColor);
        paySearchBtn.setBackgroundColor(btnColor);

        // MainActivity Home Menu CardView Id
        login = (NeumorphCardView) findViewById(R.id.login);
        adminBtn = (NeumorphCardView) findViewById(R.id.adminBtn);
        adminScheduleBtn = (NeumorphCardView) findViewById(R.id.adminScheduleBtn);
        callBtn = (NeumorphCardView) findViewById(R.id.callBtn);

        // ListView Item onClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get Information of Clicked Member Item
                MemberData selectedItem = fakeDataList.get(position);
                showMemberInfoDialog(selectedItem);
            }
        });

        // SettingBtn onClickListener
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setting.setShapeType(1);
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {setting.setShapeType(0);}
                }, 200);
                // Setting 페이지로 이동 및 메인페이지 배경색상 전달
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                intent.putExtra("background_color", backgroundColor);
                intent.putExtra("mode", mode);
                startActivity(intent);
            }
        });

        // ChangeModeBtn onClickListener
        changeMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMode.setShapeType(1);
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {changeMode.setShapeType(0);}
                }, 200);

                if(mode == 0) {
                    // DarkMode
                    backgroundColor = darkModeBackgroundColor;
                    main.setBackgroundColor(backgroundColor);

                    // Change FontColor
                    // Find and Change The Color Of All TextViews In Every Root view
                    ChangeMode.applyMainTheme(main, mode);

                    ChangeMode.setDarkShadowCardView(mainCardView);
                    ChangeMode.setDarkShadowCardView(footer_menu);

                    // MemberManagement Page CardView
                    ChangeMode.setDarkShadowCardView(input_searchId);
                    ChangeMode.setDarkShadowCardView(memberSearchBtn);
                    ChangeMode.setDarkShadowCardView(memberListCardView);

                    // AdminMain Page CardView
                    ChangeMode.setDarkShadowCardView(adminBtn);
                    ChangeMode.setDarkShadowCardView(adminScheduleBtn);
                    ChangeMode.setDarkShadowCardView(callBtn);
                    ChangeMode.setDarkShadowCardView(login);

                    // PaymentList Page CardView
                    ChangeMode.setColorFilterLight(paySearchBtn);
                    ChangeMode.setDarkShadowCardView(paySearchBtn);
                    ChangeMode.setDarkShadowCardView(payListCardView);
                    ChangeMode.setDarkShadowCardView(input_searchIdPay);

                    // ProductRegister Page CardView
                    ChangeMode.setDarkShadowCardView(input_productImage);
                    ChangeMode.setDarkShadowCardView(input_productName);
                    ChangeMode.setDarkShadowCardView(input_productQuantity);
                    ChangeMode.setDarkShadowCardView(input_productCategory);
                    ChangeMode.setDarkShadowCardView(input_productPrice);
                    ChangeMode.setDarkShadowCardView(createQRBtn);
                    ChangeMode.setDarkShadowCardView(createProductBtn);

                    // Change ImageView Color
                    ChangeMode.setColorFilterDark(adminList);
                    ChangeMode.setColorFilterDark(call);
                    ChangeMode.setColorFilterDark(adminLogin);
                    ChangeMode.setColorFilterDark(adminSchedule);

                    // Footer Menu
                    ChangeMode.setColorFilterDark(memberBtn);
                    ChangeMode.setDarkShadowCardView(memberBtn);
                    ChangeMode.setColorFilterDark(productBtn);
                    ChangeMode.setDarkShadowCardView(productBtn);
                    ChangeMode.setColorFilterDark(homeBtn);
                    ChangeMode.setDarkShadowCardView(homeBtn);
                    ChangeMode.setColorFilterDark(payHistoryBtn);
                    ChangeMode.setDarkShadowCardView(payHistoryBtn);
                    ChangeMode.setColorFilterDark(productPushBtn);
                    ChangeMode.setDarkShadowCardView(productPushBtn);
                    ChangeMode.setColorFilterDark(setting);
                    ChangeMode.setDarkShadowCardView(setting);

                    // Change ChangeMode Image & Color
                    changeMode.setImageResource(R.drawable.light);
                    ChangeMode.setColorFilterDark(changeMode);
                    ChangeMode.setDarkShadowCardView(changeMode);

                    // Change Mode Value
                    mode++;
                }
                else if(mode == 1) {
                    // LightMode
                    backgroundColor = mainBackgroundColor;
                    main.setBackgroundColor(backgroundColor);

                    // Change FontColor
                    // Find and Change The Color Of All TextViews In Every Root view
                    ChangeMode.applyMainTheme(main, mode);

                    ChangeMode.setLightShadowCardView(footer_menu);
                    ChangeMode.setLightShadowCardView(mainCardView);

                    // MemberManagement Page CardView
                    ChangeMode.setLightShadowCardView(input_searchId);
                    ChangeMode.setLightShadowCardView(memberSearchBtn);
                    ChangeMode.setLightShadowCardView(memberListCardView);

                    // AdminMain Page CardView
                    ChangeMode.setLightShadowCardView(adminBtn);
                    ChangeMode.setLightShadowCardView(adminScheduleBtn);
                    ChangeMode.setLightShadowCardView(callBtn);
                    ChangeMode.setLightShadowCardView(login);

                    // PaymentList Page CardView
                    ChangeMode.setColorFilterLight(paySearchBtn);
                    ChangeMode.setLightShadowCardView(paySearchBtn);
                    ChangeMode.setLightShadowCardView(payListCardView);
                    ChangeMode.setLightShadowCardView(input_searchIdPay);

                    // ProductRegister Page CardView
                    ChangeMode.setLightShadowCardView(input_productImage);
                    ChangeMode.setLightShadowCardView(input_productName);
                    ChangeMode.setLightShadowCardView(input_productQuantity);
                    ChangeMode.setLightShadowCardView(input_productPrice);
                    ChangeMode.setLightShadowCardView(input_productCategory);
                    ChangeMode.setLightShadowCardView(createQRBtn);
                    ChangeMode.setLightShadowCardView(createProductBtn);

                    // Change ImageView Color
                    ChangeMode.setColorFilterLight(adminList);
                    ChangeMode.setColorFilterLight(call);
                    ChangeMode.setColorFilterLight(adminLogin);
                    ChangeMode.setColorFilterLight(adminSchedule);

                    // Footer Menu
                    ChangeMode.setLightShadowCardView(memberBtn);
                    ChangeMode.setColorFilterLight(memberBtn);
                    ChangeMode.setLightShadowCardView(productBtn);
                    ChangeMode.setColorFilterLight(productBtn);
                    ChangeMode.setLightShadowCardView(homeBtn);
                    ChangeMode.setColorFilterLight(homeBtn);
                    ChangeMode.setLightShadowCardView(payHistoryBtn);
                    ChangeMode.setColorFilterLight(payHistoryBtn);
                    ChangeMode.setLightShadowCardView(productPushBtn);
                    ChangeMode.setColorFilterLight(productPushBtn);
                    ChangeMode.setLightShadowCardView(setting);
                    ChangeMode.setColorFilterLight(setting);

                    // Change ChangeMode Image & Color
                    changeMode.setImageResource(R.drawable.dark);
                    ChangeMode.setLightShadowCardView(changeMode);
                    ChangeMode.setColorFilterLight(changeMode);

                    // Change Mode Value
                    mode--;
                }
                else {
                    showErrorDialog(MainActivity.this, "임성준");
                }
            }
        });

        // AdminBtn onClickListener
        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change ShapeType to 'pressed' when clicked
                adminBtn.setShapeType(1);
                // After clicked, it changes back to 'flat'
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {adminBtn.setShapeType(0);}
                }, 200);
                // Move to AdminList Page & transfer main page background color
                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                intent.putExtra("background_color", backgroundColor);
                intent.putExtra("mode", mode);
                startActivity(intent);
            }
        });

        // AdminScheduleBtn onClickListener
        adminScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change ShapeType to 'pressed' when clicked
                adminScheduleBtn.setShapeType(1);
                // After clicked, it changes back to 'flat'
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {adminScheduleBtn.setShapeType(0);}
                }, 200);
                // Move to AdminSchedule Page & transfer main page background color
                Intent intent = new Intent(getApplicationContext(), AdminScheduleActivity.class);
                intent.putExtra("background_color", backgroundColor);
                intent.putExtra("mode", mode);
                startActivity(intent);
            }
        });

        // CallBtn onClickListener
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change ShapeType to 'pressed' when clicked
                callBtn.setShapeType(1);
                // After clicked, it changes back to 'flat'
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {callBtn.setShapeType(0);}
                }, 200);

                // Move to Dial
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:/010-1234-1234"));
                startActivity(intent);
            }
        });

        // LoginBtn onClickListener
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change ShapeType to 'pressed' when clicked
                login.setShapeType(1);
                // After clicked, it changes back to 'flat'
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {login.setShapeType(0);}
                }, 200);
                // Move to Login Page & transfer main page background color
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra("background_color", backgroundColor);
                intent.putExtra("mode", mode);
                startActivity(intent);
            }
        });

        // HomeBtn onClickListener
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change ShapeType to 'pressed' when clicked
                homeBtn.setShapeType(1);
                // After clicked, it changes back to 'flat'
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {homeBtn.setShapeType(0);}
                }, 200);
                vFlipper.setDisplayedChild(0);
            }
        });

        // MemberBtn onClickListener
        memberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change ShapeType to 'pressed' when clicked
                memberBtn.setShapeType(1);
                // After clicked, it changes back to 'flat'
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {memberBtn.setShapeType(0);}
                }, 200);
                vFlipper.setDisplayedChild(1);
            }
        });

        // ProductBtn onClickListener
        productBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change ShapeType to 'pressed' when clicked
                productBtn.setShapeType(1);
                // After clicked, it changes back to 'flat'
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {productBtn.setShapeType(0);}
                }, 200);
                vFlipper.setDisplayedChild(2);
            }
        });

        // PayHistoryBtn onClickListener
        payHistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change ShapeType to 'pressed' when clicked
                payHistoryBtn.setShapeType(1);
                // After clicked, it changes back to 'flat'
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {payHistoryBtn.setShapeType(0);}
                }, 200);
                vFlipper.setDisplayedChild(3);
            }
        });

        // ProductPushBtn onClickListener
        productPushBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change ShapeType to 'pressed' when clicked
                productPushBtn.setShapeType(1);
                // After clicked, it changes back to 'flat'
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {productPushBtn.setShapeType(0);}
                }, 200);
                vFlipper.setDisplayedChild(4);
            }
        });
    }

    // Create Fake Data
    private ArrayList<MemberData> createFakeData() {
        ArrayList<MemberData> dataList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            // Create Fake Data & Add Lis
            MemberData memberData = new MemberData(i, "Member" + i, new Date(), i * 100);
            dataList.add(memberData);
        }
        return dataList;
    }

    // Error Dialog Method
    public static void showErrorDialog(Context context, String message) {
        // Create Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("오류 발생")
                .setMessage(message)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Pressed "확인" Btn
                        dialog.dismiss(); // Close Dialog
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void showMemberInfoDialog(MemberData selectedItem) {
        // Create Dialog & Layout Setting
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_user_item_info);

        // Get TextView ID in Dialog
        TextView textViewMemberId = dialog.findViewById(R.id.textViewUserNum);
        textViewMemberId.setText("Num: " + selectedItem.getNumber());

        TextView textViewMemberName = dialog.findViewById(R.id.textViewUserId);
        textViewMemberName.setText("Name: " + selectedItem.getUserId());

        TextView textViewMemberDate = dialog.findViewById(R.id.textViewUserDate);
        textViewMemberDate.setText("Date: " + selectedItem.getJoinDate().toString());

        TextView textViewMemberAmount = dialog.findViewById(R.id.textViewUserPoint);
        textViewMemberAmount.setText("Point: $" + selectedItem.getPoint());

        // Show Dialog
        dialog.show();
    }
}