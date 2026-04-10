package com.example.kiboko_wellnessapp

import android.content.Intent
import android.health.connect.datatypes.HydrationRecord
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {
    //create a private variable to hold the interstitial ad
    private var interstitialAd : InterstitialAd?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //initialize Admob ads
        MobileAds.initialize(this)
        val adView=findViewById<AdView>(R.id.adView)
        //request ad from admob
        val adRequest= AdRequest.Builder().build()
        //show the ad
        adView.loadAd(adRequest)
        //show the interstitial ad
        loadInterstitialAd()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //find views by ID
        val healthy_tips=findViewById<Button>(R.id.healthy_tips)
        val nutrition_advice=findViewById<Button>(R.id.nutrition_advice)
        val meditation=findViewById<Button>(R.id.meditation)
        val hydrationAlert=findViewById<Button>(R.id.hydration_alert)
        val startExercise=findViewById<Button>(R.id.exercise)
        val dailyMotivation=findViewById<Button>(R.id.motivation)
        val weeklyGoals=findViewById<Button>(R.id.goals)
        val checkProgress=findViewById<Button>(R.id.progress)


        healthy_tips.setOnClickListener {
            val intent= Intent(applicationContext, HealthyRecipes::class.java)
            startActivity(intent)
        }
        nutrition_advice.setOnClickListener {
            val intent= Intent(applicationContext, NutritionAdvice::class.java)
            startActivity(intent)
        }
        meditation.setOnClickListener {
            val intent= Intent(applicationContext, Meditation::class.java)
            startActivity(intent)
        }
        hydrationAlert.setOnClickListener {
            val intent=Intent(applicationContext, Hydration_Alert::class.java)
            startActivity(intent)
        }
        startExercise.setOnClickListener {
            val intent= Intent(applicationContext, Start_Exercise::class.java)
            startActivity(intent)
        }
        dailyMotivation.setOnClickListener {
            val intent= Intent(applicationContext, DailyMotivation::class.java)
            startActivity(intent)
        }
        weeklyGoals.setOnClickListener {
            val intent= Intent(applicationContext, WeeklyGoals::class.java)
            startActivity(intent)
        }
        checkProgress.setOnClickListener {
            val intent= Intent(applicationContext, CheckProgress::class.java)
            startActivity(intent)
        }
    }
    //load the interstitial Ad
    fun loadInterstitialAd(){
        //request ad from admob
        val adRequest= AdRequest.Builder().build()
        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback(){
                //load the ad  and store it inside a variable
                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitialAd= ad
                }
                //set the variable to null if ad fails
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    interstitialAd= null
                }
            }
        )
    }
    //show the ad
    fun showInterstitialAd(){
        if(interstitialAd != null){
            interstitialAd?.show(this)
        }
    }
}