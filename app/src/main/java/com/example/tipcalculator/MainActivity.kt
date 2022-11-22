package com.example.tipcalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*

private const val initial_tip=0
class MainActivity : AppCompatActivity() {
lateinit var seek:SeekBar
lateinit var baseamount:EditText
lateinit var percentage:TextView
lateinit var tipamount:TextView
lateinit var totalamount:TextView
lateinit var dec:Button
lateinit var inc:Button
lateinit var num:TextView
lateinit var sp:TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        seek=findViewById(R.id.seek)
        baseamount=findViewById(R.id.amountbase)
        percentage=findViewById(R.id.percent)
        tipamount=findViewById(R.id.tipamount)
        totalamount=findViewById(R.id.totalamount)
        sp=findViewById(R.id.splitAmount)
        dec=findViewById(R.id.sub)
        inc=findViewById(R.id.add)
        num=findViewById(R.id.view)
        percentage.text="$initial_tip%"
        seek.progress= initial_tip



        var incnum=1
        dec.setOnClickListener{
            if(incnum==1)
            {
                incnum==1
            }
            else {
                incnum--

                num.text = incnum.toString()
            }
            computeTipAndTotal()

        }
        inc.setOnClickListener{
            if(incnum>20)
            {
             incnum=20
                Toast.makeText(this, "Itni Jagah hi nahi thi yahan", Toast.LENGTH_SHORT).show()
            }
            else {
                incnum++
                num.text = incnum.toString()
            }
            computeTipAndTotal()

        }

        seek.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
//                here p1 is the progress of seekbar
                percentage.text= "$progress%"
                computeTipAndTotal()


            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        baseamount.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                computeTipAndTotal()
            }

        })
    }


    private fun computeTipAndTotal() {
        if(baseamount.text.isEmpty())
        {
            tipamount.text=""
//            totalamount.text=seek.progress.toString()
            totalamount.text=""

            return

        }
        val amount=baseamount.text.toString().toDouble()
        val tipPercent = seek.progress
        val tipamt = amount * tipPercent / 100
        val totalamt = amount + tipamt
        val splitamt=totalamt / num.text.toString().toInt()
        totalamount.text = "%.2f".format(totalamt)
        tipamount.text= "%.2f".format(tipamt)
        sp.text="%.2f".format(splitamt)

    }
}