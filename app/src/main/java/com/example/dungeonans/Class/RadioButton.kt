//package com.example.dungeonans.Class
//
//import android.util.Log
//import android.util.TypedValue
//import android.view.View
//import android.widget.RadioButton
//import android.widget.RadioGroup
//import com.example.dungeonans.R
//
//class RadioButton(view:View) {
//
//    private fun setHashTag(view: View) {
//        var radioGroup : RadioGroup = view.findViewById(R.id.radioGroup)
//        var radioButtonText = resources.getStringArray(R.array.hashtaglist)
//
//        // 라디오 버튼 생성
//        for (index in 0 until radioButtonText.count()) {
//            var radioButton = layoutInflater.inflate(R.layout.hashtag_radiobutton,null)
//            radioButton.id = index
//            var buttonParams = RadioGroup.LayoutParams(
//                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,70f,resources.displayMetrics).toInt(),
//                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,30f,resources.displayMetrics).toInt())
//            buttonParams.setMargins(
//                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10f,resources.displayMetrics).toInt(),0,0,0)
//            radioButton.layoutParams = buttonParams
//            radioGroup.addView(radioButton)
//        }
//
//        // 라디오 버튼 텍스트 설정, 선택 해제 로직
//        for (index in 0 until radioButtonText.count()) {
//            var radioButton = view.findViewById<RadioButton>(index)
//            radioButton.text = radioButtonText[index]
//            radioButton.setOnClickListener{
//                if (selectedBtn == index) {
//                    radioButton.isChecked = false
//                    view.findViewById<RadioButton>(selectedBtn!!).setTextColor(resources.getColor(R.color.black,null))
//                }
//                selectedBtn = index
//            }
//        }
//        // 라디오 버튼 선택 해제 로직
//        radioGroup.setOnCheckedChangeListener{ _, checkedId ->
//            if (selectedBtn != null) {
//                view.findViewById<RadioButton>(selectedBtn!!).setTextColor(resources.getColor(R.color.black,null))
//            }
//            when(checkedId) {
//                checkedId ->  {
//                    Log.d("Tag","?")
//                    var checkedBtn = view.findViewById<RadioButton>(checkedId)
//                    checkedBtn.setTextColor(resources.getColor(R.color.white,null))
//                }
//            }
//        }
//    }
//}