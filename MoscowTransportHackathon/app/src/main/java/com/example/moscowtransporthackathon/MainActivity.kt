package com.example.moscowtransporthackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import com.example.moscowtransporthackathon.lib.RulerUnit
import com.example.moscowtransporthackathon.lib.RulerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private val animationHandler = Handler()
    lateinit var linearLayoutBottomSheet : LinearLayout
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var persistentBtn : MaterialButton
    private lateinit var seekBar : SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        persistentBtn = findViewById(R.id.persistentBtn)
        linearLayoutBottomSheet = findViewById(R.id.persistent_bottom_sheet)

        bottomSheetBehavior = BottomSheetBehavior.from(linearLayoutBottomSheet)

        bottomSheetBehavior.addBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback(){
            override fun onStateChanged(bottomSheet: View, state: Int) {
                print(state)
                when (state) {

                    BottomSheetBehavior.STATE_HIDDEN -> {
                        //persistentBtn.text = "Show Bottom Sheet"
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {}
                        //persistentBtn.text = "Close Bottom Sheet"
                    BottomSheetBehavior.STATE_COLLAPSED ->{}
                        //persistentBtn.text = "Show Bottom Sheet"
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {

                    }
                }
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        })


        var rulerView = RulerView(this)
        rulerView = findViewById(R.id.rulerView)
        rulerView.cameraDistance = 2.0f
        rulerView.unit = RulerUnit.CM
        rulerView.pxTOdp(1000f)
        rulerView.coefficient = 2f

        persistentBtn.setOnClickListener {
            expandCollapseSheet()
        }
        seekBar = findViewById(R.id.seekBar)
        seekBar.max = 100
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (progress <50){
                    seekBar?.thumb = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_baseline_airline_seat_recline_normal_24)
                }else {
                    seekBar?.thumb = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_baseline_transfer_within_a_station_24)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //TODO("Not yet implemented")
            }

        })


//        animationHandler.post(object : Runnable {
//            override fun run() {
//                hopAnimation()
//                animationHandler.postDelayed(this, 1000)
//            }
//        })

    }
    fun expandCollapseSheet() {
        if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            //persistentBtn.text = "Close Bottom Sheet"
        } else {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            //persistentBtn.text = "Show Bottom Sheet"
        }

    }

    private fun hopAnimation() {
        val animation = linearLayoutBottomSheet
            .animate()
            .translationYBy(-40f)
            .setDuration(200)
        animation.withEndAction {
            linearLayoutBottomSheet.animate().translationYBy(40f).duration = 200
        }
    }

}