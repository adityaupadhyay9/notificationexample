package com.app.notificationexample

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import androidx.core.content.res.ResourcesCompat

class MainActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder:Notification.Builder
    private val channelid="i.apps.notifications"
    private val description="Test Notification"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn=findViewById<Button>(R.id.btn)
        notificationManager =getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        btn.setOnClickListener(){
            val intent=Intent(this,Afternotification::class.java)


            val pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
            val contentView=RemoteViews(packageName,R.layout.activity_afternotification)



                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                    notificationChannel= NotificationChannel(channelid,description,NotificationManager.IMPORTANCE_HIGH)
                    notificationChannel.enableLights(true)
                    notificationChannel.lightColor=Color.GREEN
                    notificationChannel.enableVibration(false)
                    notificationManager.createNotificationChannel(notificationChannel)

                    builder= Notification.Builder(this,channelid)
                        .setContent(contentView)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setLargeIcon(BitmapFactory.decodeResource(this.resources,
                            R.drawable.ic_launcher_background))
                        .setContentIntent(pendingIntent)
                }else{

                    builder= Notification.Builder(this)
                        .setContent(contentView)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.drawable.ic_launcher_background))
                        .setContentIntent(pendingIntent)
    
                }
            notificationManager.notify(1234,builder.build())
        }

    }
}