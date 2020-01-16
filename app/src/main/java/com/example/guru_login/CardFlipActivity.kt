package com.example.guru_login

import android.os.Bundle
import android.util.Log
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.card_flip.*

class CardFlipActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {

    private val TAG = "ClassName"

    var isShowingBackLayout = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_flip)

//        if (savedInstanceState == null) {
//            supportFragmentManager
//                .beginTransaction()
//                .add(R.id.card_flip_container, CardFlipFront())
//                .commit()
//        } else {
//            isShowingBackLayout = supportFragmentManager.backStackEntryCount > 0
//        }
//
//        supportFragmentManager.addOnBackStackChangedListener(this);

        getFrontFragment(savedInstanceState)

        //SwipeDetector.kt와 연결해줍니다.
        val swipeDetector = SwipeDetector()

        val card_flip_container = card_flip_container

        card_flip_container.setOnTouchListener(swipeDetector)

        //온클릭리스너(여기에서 TODO!)
        card_flip_container.setOnClickListener {
            //스와이프
            if (swipeDetector.swipeDetected()) {
                //스와이프 처리
                if (swipeDetector.action.equals(SwipeDetector.Action.LR)) {

                    Log.d(TAG, "signInWithEmail:success")
                    Toast.makeText(baseContext, "왼쪽으로 슬라이드",
                        Toast.LENGTH_SHORT).show()
                    //여기에 적용할 코드를 입력해주세요~
                } else if (swipeDetector.action.equals(SwipeDetector.Action.RL)) {
                    //여기에 적용할 코드를 입력해주세요~
                    Log.d(TAG, "signInWithEmail:success")
                    Toast.makeText(baseContext, "오른쪽으로 슬라이드",
                        Toast.LENGTH_SHORT).show()
                }
            }else {
                flipCard()
            }
        }



        val study_flip_container = findViewById(R.id.card_flip_container) as RelativeLayout
        study_flip_container.setOnClickListener {
            flipCard()
        }
    }

    override fun onBackStackChanged() {
        isShowingBackLayout = (supportFragmentManager.getBackStackEntryCount() > 0);
    }

    private fun flipCard() {
        if (isShowingBackLayout) {
            supportFragmentManager.popBackStack();
            return;
        }
        isShowingBackLayout = true;
        supportFragmentManager.beginTransaction()
            //커스텀 애니메이션
            .setCustomAnimations(
                R.animator.cardflip_right_in, R.animator.cardflip_right_out,
                R.animator.cardflip_left_in, R.animator.cardflip_left_out)
            // 뒷면으로 바뀜
            .replace(R.id.card_flip_container, CardFlipBack())
            // 뒤로가기 누르면 앞면을 보여줌
            .addToBackStack(null)
            .commit();
    }

    private fun getFrontFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.card_flip_container, CardFlipFront())
                    .commit()
        } else {
            isShowingBackLayout = supportFragmentManager.backStackEntryCount > 0
        }
        supportFragmentManager.addOnBackStackChangedListener(this);
    }
}