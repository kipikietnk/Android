package com.example.cucuakythu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtDiem;
    CheckBox cb1, cb2, cb3;
    SeekBar sb1, sb2,sb3;
    ImageButton ibtPlay;
    int Points = 100;
    SharedPreferences Luudiem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        sb1.setEnabled(false);
        sb2.setEnabled(false);
        sb3.setEnabled(false);
        txtDiem.setText(Points+"");

        Luudiem = getSharedPreferences("Diemso",MODE_PRIVATE);//world readable, writable

        Points = Luudiem.getInt("diem",100);

        txtDiem.setText(Points + "");

        CountDownTimer countDownTimer = new CountDownTimer(60000, 300) {
            @Override
            public void onTick(long l) {
                int number = 5;
                Random random = new Random();
                int one = random.nextInt(number);
                int two = random.nextInt(number);
                int three = random.nextInt(number);

                if(sb1.getProgress()>=sb1.getMax()){
                    this.cancel();
                    Toast.makeText(MainActivity.this,"ONE WIN",Toast.LENGTH_LONG).show();
                    ibtPlay.setVisibility(View.VISIBLE);
                    if(cb1.isChecked()){
                        Points += 10;
                        LuuDiem();
                        Toast.makeText(MainActivity.this, "Bạn thắng +10", Toast.LENGTH_SHORT).show();
                    }else{
                        Points -= 5;
                        LuuDiem();
                        Toast.makeText(MainActivity.this, "Bạn thua -5", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(Points+"");
                    EnableCheckBox();
                }
                if(sb2.getProgress()>=sb2.getMax()){
                    this.cancel();
                    Toast.makeText(MainActivity.this,"TWO WIN",Toast.LENGTH_LONG).show();
                    ibtPlay.setVisibility(View.VISIBLE);
                    if(cb2.isChecked()){
                        Points += 10;
                        LuuDiem();
                        Toast.makeText(MainActivity.this, "Bạn thắng +10", Toast.LENGTH_SHORT).show();
                    }else{
                        Points -= 5;
                        LuuDiem();
                        Toast.makeText(MainActivity.this, "Bạn thua -5", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(Points+"");
                    EnableCheckBox();
                }
                if(sb3.getProgress()>=sb3.getMax()){
                    this.cancel();
                    Toast.makeText(MainActivity.this,"THREE WIN",Toast.LENGTH_LONG).show();
                    ibtPlay.setVisibility(View.VISIBLE);
                    if(cb3.isChecked()){
                        Points += 10;
                        LuuDiem();
                        Toast.makeText(MainActivity.this, "Bạn thắng +10", Toast.LENGTH_SHORT).show();
                    }else{
                        Points -= 5;
                        LuuDiem();
                        Toast.makeText(MainActivity.this, "Bạn thua -5", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(Points+"");
                    EnableCheckBox();

                }

                sb1.setProgress(sb1.getProgress()+one);
                sb2.setProgress(sb2.getProgress()+two);
                sb3.setProgress(sb3.getProgress()+three);

            }

            @Override
            public void onFinish() {

            }
        };
        ibtPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb1.isChecked() || cb2.isChecked() || cb3.isChecked()) {
                    sb1.setProgress(0);
                    sb2.setProgress(0);
                    sb3.setProgress(0);
                    ibtPlay.setVisibility(View.INVISIBLE);
                    countDownTimer.start();
                    DisableCheckBox();
                }else{
                    Toast.makeText(MainActivity.this, "Vui lòng đặt cược", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cb2.setChecked(false);
                cb3.setChecked(false);
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cb1.setChecked(false);
                cb3.setChecked(false);
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                cb1.setChecked(false);
                cb2.setChecked(false);
            }
        });

    }
    private void AnhXa(){
        txtDiem = (TextView) findViewById(R.id.textviewPoints);
        cb1 = (CheckBox) findViewById(R.id.checkbox1);
        cb2 = (CheckBox) findViewById(R.id.checkbox2);
        cb3 = (CheckBox) findViewById(R.id.checkbox3);
        sb1 = (SeekBar) findViewById(R.id.seekbar1);
        sb2 = (SeekBar) findViewById(R.id.seekbar2);
        sb3 = (SeekBar) findViewById(R.id.seekbar3);
        ibtPlay = (ImageButton) findViewById(R.id.imagebuttonplay);
    }
    private void EnableCheckBox(){
        cb1.setEnabled(true);
        cb2.setEnabled(true);
        cb3.setEnabled(true);
    }
    private void DisableCheckBox(){
        cb1.setEnabled(false);
        cb2.setEnabled(false);
        cb3.setEnabled(false);
    }
    private void LuuDiem(){
        SharedPreferences.Editor editor = Luudiem.edit();
        editor.putInt("diem",Points);
        editor.commit();
    }
}