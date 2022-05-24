package ua.amv0107.mysupercalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import net.objecthunter.exp4j.ExpressionBuilder
import ua.amv0107.mysupercalc.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btn0.setOnClickListener { setTextFields("0") }
            btn1.setOnClickListener { setTextFields("1") }
            btn2.setOnClickListener { setTextFields("2") }
            btn3.setOnClickListener { setTextFields("3") }
            btn4.setOnClickListener { setTextFields("4") }
            btn5.setOnClickListener { setTextFields("5") }
            btn6.setOnClickListener { setTextFields("6") }
            btn7.setOnClickListener { setTextFields("7") }
            btn8.setOnClickListener { setTextFields("8") }
            btn9.setOnClickListener { setTextFields("9") }

            btnDot.setOnClickListener { setTextFields(".") }
            btnMinus.setOnClickListener { setTextFields("-") }
            btnPlus.setOnClickListener { setTextFields("+") }
            btnDivision.setOnClickListener { setTextFields("/") }
            btnMultiply.setOnClickListener { setTextFields("*") }
            btnBracketOpen.setOnClickListener { setTextFields("(") }
            btnBracketClose.setOnClickListener { setTextFields(")") }

            btnClear.setOnClickListener {
                workingsTextView.text = ""
                resultTextView.text = ""
            }

            btnBack.setOnClickListener {
                var str = workingsTextView.text.toString()
                if (str.isNotEmpty())
                    workingsTextView.text = str.substring(0, str.length - 1)
                resultTextView.text = ""
            }

            btnEqual.setOnClickListener {
                try {
                    val ex = ExpressionBuilder(workingsTextView.text.toString()).build()
                    val result = ex.evaluate()

                    val longRes = result.toLong()
                    if (result == longRes.toDouble())
                        resultTextView.text = longRes.toString()
                    else
                        resultTextView.text = result.toString()
                } catch (ex: Exception) {
                    Log.d("Error", "Message: ${ex.message}")
                }
            }

        }
    }

    private fun setTextFields(str: String) {
        if(binding.resultTextView.text != ""){
            binding.workingsTextView.text = binding.resultTextView.text
            binding.resultTextView.text = ""
        }
        binding.workingsTextView.append(str)
    }
}