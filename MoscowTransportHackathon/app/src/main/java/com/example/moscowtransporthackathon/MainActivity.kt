package com.example.moscowtransporthackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.example.moscowtransporthackathon.lib.RulerUnit
import com.example.moscowtransporthackathon.lib.RulerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private val animationHandler = Handler()
    lateinit var linearLayoutBottomSheet : LinearLayout
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var persistentBtn : MaterialButton

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