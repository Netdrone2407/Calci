package com.example.calci

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.util.Linkify
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.Stack
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    var s:String=""
    var l:String="="
    lateinit var text2:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn1=findViewById<TextView>(R.id.btn1)
        val btn2=findViewById<TextView>(R.id.btn2)
        val btn3=findViewById<TextView>(R.id.btn3)
        val btn4=findViewById<TextView>(R.id.btn4)
        val btn5=findViewById<TextView>(R.id.btn5)
        val btn6=findViewById<TextView>(R.id.btn6)
        val btn7=findViewById<TextView>(R.id.btn7)
        val btn8=findViewById<TextView>(R.id.btn8)
        val btn9=findViewById<TextView>(R.id.btn9)
        val btn10=findViewById<TextView>(R.id.btn10)
        val btn11=findViewById<TextView>(R.id.btn11)
        val btn12=findViewById<TextView>(R.id.btn12)
        val btn13=findViewById<TextView>(R.id.btn13)
        val btn14=findViewById<TextView>(R.id.btn14)
        val btn15=findViewById<TextView>(R.id.btn15)
        val btn16=findViewById<TextView>(R.id.btn16)
        val btn17=findViewById<TextView>(R.id.btn17)
        val ac=findViewById<TextView>(R.id.ac)
        val run=findViewById<Button>(R.id.run)
        var text1=findViewById<TextView>(R.id.text)
//        var click=findViewById<TextView>(R.id.text4)
//        click.setOnClickListener{
//            val url = "https://www.geeksforgeeks.org/textview-in-kotlin/"
//            val intent = Intent(Intent.ACTION_VIEW)
//            intent.data = Uri.parse(url)
//            startActivity(intent)
//        }
        text2=findViewById(R.id.text1)
        btn1.setOnClickListener{
            s=s+"1"
            text1.text=s;
            running()
        }
        btn2.setOnClickListener{
            s=s+"2"
            text1.text=s;
            running()
        }
        btn3.setOnClickListener{
            s=s+"3"
            text1.text=s;
            running()
        }
        btn4.setOnClickListener{
            if (s.length>=1){
                s=s.dropLast(1)
            }

            text1.text=s;
            running()
        }
        btn5.setOnClickListener{
            s=s+"4"
            text1.text=s;
            running()
        }
        btn6.setOnClickListener{
            s=s+"5"
            text1.text=s;
            running()
        }
        btn7.setOnClickListener{
            s=s+"6"
            text1.text=s;
            running()
        }
        btn8.setOnClickListener{
            s=s+"+"
            text1.text=s;

        }
        btn9.setOnClickListener{
            s=s+"7"
            text1.text=s;
            running()
        }
        btn10.setOnClickListener{
            s=s+"8"
            text1.text=s;
            running()
        }
        btn11.setOnClickListener{
            s=s+"9"
            text1.text=s;
            running()
        }
        btn12.setOnClickListener{
            s=s+"-"
            text1.text=s;
        }
        btn13.setOnClickListener{
            s=s+"0"
            text1.text=s;
            running()
        }
        btn14.setOnClickListener{
            s=s+"."
            text1.text=s;
        }
        btn15.setOnClickListener{
            s=s+"/"
            text1.text=s;

        }
        btn16.setOnClickListener{
            s=s+"*"
            text1.text=s;

        }
        btn17.setOnClickListener{
            s=s+"%"
            text1.text=s;

        }
        ac.setOnClickListener{
          s="";
            l=""
            text1.text=s
            text2.text=l
            l="="


        }
        run.setOnClickListener{
               running()
        }


    }
    private fun running(){
        val a = Stack<Double>()
        val b = Stack<Char>()
        var f:Char=' '
        var o=false;
        if(s[s.length-1]=='+' || s[s.length-1]=='/' || s[s.length-1]=='-' || s[s.length-1]=='/' ||s[s.length-1]=='%'  || s[s.length-1]=='.' ){
            o=true
            f=s[s.length-1]
            s=s.dropLast(1)
        }
        var num = 0.0
        var j=0
        var p=false
        for (i in s.indices) {
            if (s[i] == '+' || s[i] == '-' || s[i] == '*' || s[i] == '/' || s[i]=='%') {
                val m = 10.0.pow(j).toInt()
                  num=num/m
                p=false
                j=0
                a.push(num)
                num = 0.0
                b.push(s[i])
            }
            else if(s[i]=='.') {
                p=true
            }
            else{
                val d = s[i] - '0'
                if(p==true){
                    j++;
                }
                num = num * 10 + d
            }
        }
        val m = 10.0.pow(j).toInt()
        num=num/m
        a.push(num)
        val c= Stack<Double>()
        val d = Stack<Char>()
        while (!b.empty()) {
            val i=b.pop()
            val f = a.pop()
            if(i=='/'){
            val g = a.pop()
//            when (b.pop()) {
//                '+' -> a.push(f + g)
//                '-' -> a.push(g - f)
//                '*' -> a.push(f * g)
//                '/' -> a.push(g / f)
//                '%' -> a.push(g%f)
//            }
                a.push(g/f)

     }
            else{
                d.push(i)
                c.push(f)
            }
     }
        c.push(a.pop())
        while (!d.empty()) {
            val i=d.pop()
            val f = c.pop()
            if(i=='*'){
                val g = c.pop()
                c.push(g*f)
            }
            else{
                b.push(i)
                a.push(f);
            }

        }
        a.push(c.pop())
        while (!b.empty()) {
            val f = a.pop()
            val g = a.pop()
              when (b.pop()) {
                '+' -> a.push(f + g)
                '-' -> a.push(g - f)
              }

    }

        if(o){
        s=s+f}
        l=l+a.peek().toString()
        text2.text=l
        l="="
    }

}


