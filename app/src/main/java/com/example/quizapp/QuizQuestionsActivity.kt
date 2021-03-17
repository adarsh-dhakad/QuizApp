package com.example.quizapp


import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private  var mCurrentPosition: Int = 1
    private var mQuestionList:ArrayList<Question>? = null
    private  var mSelectedOptionPosition : Int = 0
    private var mCorrectAnswers:Int = 0;
   private var mUserName: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        mQuestionList = Constants.getQuestions()
      mUserName = intent.getStringExtra(Constants.USER_NAME)
      setQuestions()
        var tv_option_one = findViewById<TextView>(R.id.tv_optionOne)
        val tv_optionTwo = findViewById<TextView>(R.id.tv_optionTwo)
        val tv_optionThree = findViewById<TextView>(R.id.tv_optionThree)
        val tv_optionFour = findViewById<TextView>(R.id.tv_optionFour)
        val btn_submit = findViewById<Button>(R.id.btn_submit)
        tv_option_one.setOnClickListener(this)
        tv_optionTwo.setOnClickListener(this)
        tv_optionThree.setOnClickListener(this)
        tv_optionFour.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
        
    }
    private fun setQuestions(){
        val tv_question = findViewById<TextView>(R.id.tv_question)
        val progressBar = findViewById<ProgressBar>(R.id.progress_horizontal)
        // tv_question.text = questionList[1].toString()

        val question:Question? = mQuestionList?.get( mCurrentPosition - 1)

        defaultOptionaView()

        if(mCurrentPosition == mQuestionList!!.size){
            val btn_submit = findViewById<Button>(R.id.btn_submit)
            btn_submit.text = "FINISH"
        }else{
            val btn_submit = findViewById<Button>(R.id.btn_submit)
            btn_submit.text = "SUBMIT"
        }

        progressBar.progress = mCurrentPosition
        val tv_progress = findViewById<TextView>(R.id.tv_progress)
        tv_progress.text = "$mCurrentPosition / ${progressBar.max} "
        tv_question.text = question!!.question
        val iv_flag = findViewById<ImageView>(R.id.iv_flag)
        iv_flag.setImageResource(question.image)
        val tv_option_one = findViewById<TextView>(R.id.tv_optionOne)
       tv_option_one.text = question.optionOne
        val tv_optionTwo = findViewById<TextView>(R.id.tv_optionTwo)
        tv_optionTwo.text = question.optionTwo
        val tv_optionThree = findViewById<TextView>(R.id.tv_optionThree)
        tv_optionThree.text = question.optionThree
        val tv_optionFour = findViewById<TextView>(R.id.tv_optionFour)
        tv_optionFour.text = question.optionFour
      //  val btn_submit = findViewById<Button>(R.id.btn_submit)


    }
    private  fun defaultOptionaView(){
        val tv_option_one = findViewById<TextView>(R.id.tv_optionOne)
        val tv_optionTwo = findViewById<TextView>(R.id.tv_optionTwo)
        val tv_optionThree = findViewById<TextView>(R.id.tv_optionThree)
        val tv_optionFour = findViewById<TextView>(R.id.tv_optionFour)
        val options = ArrayList<TextView>()
        options.add(0,tv_option_one)
        options.add(1,tv_optionTwo)
        options.add(2,tv_optionThree)
        options.add(3,tv_optionFour)
        for(option in options){
            option.setTextColor(Color.parseColor("#1F0940"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                    this,
                    R.drawable.default_option_border_bg

            )
        }
    }

    override fun onClick(v: View?) {
    when(v?.id){
        R.id.tv_optionOne ->{
            val tv_optionOne = findViewById<TextView>(R.id.tv_optionOne)
            selectedOptionView(tv_optionOne,1)
        }
        R.id.tv_optionTwo ->{
            val tv_optionTwo = findViewById<TextView>(R.id.tv_optionTwo)
            selectedOptionView(tv_optionTwo,2)
        }
        R.id.tv_optionThree ->{
            val tv_optionThree = findViewById<TextView>(R.id.tv_optionThree)
            selectedOptionView(tv_optionThree,3)
        }
        R.id.tv_optionFour ->{
            val tv_optionFour = findViewById<TextView>(R.id.tv_optionFour)
            selectedOptionView(tv_optionFour,4)

        }
        R.id.btn_submit ->{

            if (mSelectedOptionPosition == 0){
                mCurrentPosition++
                when{
                    mCurrentPosition <= mQuestionList!!.size ->{
                        setQuestions()
                    }else ->{
                    val intent = Intent(this,Score_Activity::class.java)
                    intent.putExtra(Constants.USER_NAME,mUserName)
                    intent.putExtra(Constants.CORRECT_ANSWER,mCorrectAnswers)
                    intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionList!!.size)
                    startActivity(intent)

                        Toast.makeText(this,"You have successfully completed the Quiz",
                                Toast.LENGTH_SHORT).show()
                    }
                }
            }else{

                val question = mQuestionList?.get(mCurrentPosition -1)

                if(question!!.correctAnswer != mSelectedOptionPosition){

                    answerView(mSelectedOptionPosition,R.drawable.wrong_option_border_bg)
                }else{
                    mCorrectAnswers++
                }
                answerView(question.correctAnswer,R.drawable.correct_option_border_bg)
                if (mCurrentPosition == mQuestionList!!.size){
                    val btn_submit = findViewById<Button>(R.id.btn_submit)
                    btn_submit.setText("FINISH")
                }else{
                    val btn_submit = findViewById<Button>(R.id.btn_submit)
                    btn_submit.setText("GO TO NEXT QUESTION")
                }

            }
            mSelectedOptionPosition = 0;
        }

    }
    }
    private  fun answerView(answer:Int , drawableView:Int){
        when(answer){
            1 ->{
                val tv_option_one = findViewById<TextView>(R.id.tv_optionOne)
                tv_option_one.background = ContextCompat.getDrawable(
                        this,drawableView
                )
            }
            2 ->{
                val tv_option_one = findViewById<TextView>(R.id.tv_optionTwo)
                tv_option_one.background = ContextCompat.getDrawable(
                        this,drawableView
                )
            }
            3 ->{
                val tv_option_one = findViewById<TextView>(R.id.tv_optionThree)
                tv_option_one.background = ContextCompat.getDrawable(
                        this,drawableView
                )
            }
            4 ->{
                val tv_option_one = findViewById<TextView>(R.id.tv_optionFour)
                tv_option_one.background = ContextCompat.getDrawable(
                        this,drawableView
                )
            }
        }

    }
    private  fun selectedOptionView(tv:TextView,selectedOptionNumber:Int){
        defaultOptionaView()
        mSelectedOptionPosition = selectedOptionNumber
       tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
                this,
                R.drawable.selected_option_border_bg )
    }
}