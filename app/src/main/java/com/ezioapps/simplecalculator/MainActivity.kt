package com.ezioapps.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.ArithmeticException

class MainActivity : AppCompatActivity() {


    var lastNumeric = false
    var lastDot = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnDigit(view: View) {
        textAnswer.append((view as Button).text)
        lastNumeric = true
    }
    fun btnClear(view : View){
        textAnswer.text = ""
        lastDot = false
        lastNumeric = false
    }

    fun btnDot(view: View){
        if (lastNumeric && !lastDot){
            textAnswer.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun btnOperator(view: View){

        if (lastNumeric && !isOperatorAdded(textAnswer.text.toString())){

                textAnswer.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }


    private fun removeZeroAfterDot(value: String) :String{


        return if (value.contains(".0")){
            value.substring(0, value.length - 2)
        } else{
            value
        }
    }

    private fun isOperatorAdded(value : String) : Boolean {

         return if (value.startsWith("-"))
             false
         else value.contains("+") || value.contains("-")
                              || value.contains("*") || value.contains("/")
    }

    fun btnEqual(view: View) {

        if(lastNumeric){

            var answerTextValue = textAnswer.text.toString()
            var prefix = ""
            try {

                if (answerTextValue.startsWith("-")){

                    prefix = "-"
                    answerTextValue = answerTextValue.substring(1)
                }

                when {
                    answerTextValue.contains("+") -> {
                        var answer = answerTextValue.split("+")
                        var one = answer[0]
                        var two = answer[1]
                        if (!prefix.isEmpty()){
                            one = "-$one"
                        }
                        textAnswer.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())


                    }
                    answerTextValue.contains("-") -> {
                        val answer = answerTextValue.split("-")
                        var one = answer[0]
                        val two = answer[1]

                        if (!prefix.isEmpty()){
                            one = "-$one"
                        }
                        textAnswer.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                    }
                    answerTextValue.contains("*") -> {
                        val answer = answerTextValue.split("*")
                        var one = answer[0]
                        val two = answer[1]

                        if (!prefix.isEmpty()){
                            one = "-$one"
                        }
                        textAnswer.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                    }
                    answerTextValue.contains("/") -> {
                        var answer = answerTextValue.split("/")
                        var one = answer[0]
                        var two = answer[1]

                        if (!prefix.isEmpty()){
                            one = "-$one"
                        }
                        textAnswer.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                    }
                }

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }
}


