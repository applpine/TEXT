package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_result;

    //第一个操作数
    private String firstNum ="";
    //操作符
    private String operator ="";
    //第二个操作数
    private String secondNum ="";
    //结果
    private String result ="";
    //显示内容
    private String showText ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        //从本局文件中获取tv_result的文本视图
         tv_result = findViewById(R.id.tv_result);
         //给每个按钮注册监听器
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_divide).setOnClickListener(this); // “除法”按钮
        findViewById(R.id.btn_multiply).setOnClickListener(this); // “乘法”按钮
        findViewById(R.id.btn_clear).setOnClickListener(this); // “清除”按钮
        findViewById(R.id.btn_seven).setOnClickListener(this); // 数字7
        findViewById(R.id.btn_eight).setOnClickListener(this); // 数字8
        findViewById(R.id.btn_nine).setOnClickListener(this); // 数字9
        findViewById(R.id.btn_plus).setOnClickListener(this); // “加法”按钮
        findViewById(R.id.btn_four).setOnClickListener(this); // 数字4
        findViewById(R.id.btn_five).setOnClickListener(this); // 数字5
        findViewById(R.id.btn_six).setOnClickListener(this); // 数字6
        findViewById(R.id.btn_minus).setOnClickListener(this); // “减法”按钮
        findViewById(R.id.btn_one).setOnClickListener(this); // 数字1
        findViewById(R.id.btn_two).setOnClickListener(this); // 数字2
        findViewById(R.id.btn_three).setOnClickListener(this); // 数字3
        findViewById(R.id.btn_reciprocal).setOnClickListener(this); // 求倒数按钮
        findViewById(R.id.btn_zero).setOnClickListener(this); // 数字0
        findViewById(R.id.btn_dot).setOnClickListener(this); // “小数点”按钮
        findViewById(R.id.btn_equal).setOnClickListener(this); // “等号”按钮
        findViewById(R.id.ib_sqrt).setOnClickListener(this); // “开平方”按钮
    }

    @Override
    public void onClick(View view) {
        String inputText;
        //判断根号
        if(view.getId() == R.id.ib_sqrt){
            inputText = "√";
        }
        else{
            inputText = ( (TextView)view ).getText().toString();
        }
        switch (view.getId()){
            //点击清理C
            case R.id.btn_clear:
                clear();
                break;
            // 点击了取消按钮
            case R.id.btn_cancel:
                break;
            // 点击了加、减、乘、除按钮
            case R.id.btn_plus:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                operator = inputText;
                refreshText(showText + operator);
                break;
                // 点击了等号按钮
            case R.id.btn_equal:
                // 加减乘除四则运算
                double calculate_result = calculateFour();
                // 基本数据类型都可以通过String.valueOf()方法转化为字符串表示形式
                refreshOperate(String.valueOf(calculate_result));
                refreshText(showText + "=" + result);
                break;
            // 点击了开根号按钮
            case R.id.ib_sqrt:
                double sqrt_result = Math.sqrt(Double.parseDouble(firstNum));
                refreshOperate(String.valueOf(sqrt_result));
                refreshText(showText + "√=" + result);
                break;
            // 点击了求倒数按钮
            case R.id.btn_reciprocal:
                double reciprocal_result = 1.0 / Double.parseDouble(firstNum);
                refreshOperate(String.valueOf(reciprocal_result));
                refreshText(showText + "/=" + result);
                break;
            // 点击了其他按钮，包括数字和小数点
            default:
                // 上次的运算结果已经出来了
                if (result.length() > 0 && operator.equals("")) {
                    clear();
                }

                // 无运算符，则继续拼接第一个操作数
                if (operator.equals("")) {
                    firstNum = firstNum + inputText;
                } else {
                    // 有运算符，则继续拼接第二个操作数
                    secondNum = secondNum + inputText;
                }
                // 整数不需要前面的0
                if (showText.equals("0") && !inputText.equals(".")) {
                    refreshText(inputText);
                } else {
                    refreshText(showText + inputText);
                }
                break;
        }
    }

    private double calculateFour() {
        switch(operator)
        {
            case "+" :   //类型转换Double.parseDouble
                return Double.parseDouble(firstNum) + Double.parseDouble(secondNum);
            case "－":
                return Double.parseDouble(firstNum) - Double.parseDouble(secondNum);
            case "×":
                return Double.parseDouble(firstNum) * Double.parseDouble(secondNum);
            default:
                return Double.parseDouble(firstNum) / Double.parseDouble(secondNum);
        }
    }

    private void clear() {
        refreshOperate("");
        refreshText("");
    }
    //刷新运算结果
    private void refreshOperate(String new_result)
    {
        result = new_result;
        firstNum = result;
        secondNum = "";
        operator = "";
    }

    //刷新文本
    private void refreshText(String text)
    {
        showText = text;
        tv_result.setText(showText);
    }
}