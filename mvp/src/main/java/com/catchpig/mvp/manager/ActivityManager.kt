package com.catchpig.mvp.manager

import android.app.Activity
import android.content.Intent
import java.util.*

object ActivityManager {
    private var activities = LinkedList<Activity>()
    /**
     * 添加activity
     */
    fun addActivity(activity: Activity){
        activities.add(activity)
    }

    /**
     * 删除activity
     */
    fun removeActivity(activity: Activity?){
        activities.remove(activity)
    }

    /**
     * 获取最顶层的activity
     */
    fun getTopActivity():Activity{
        return activities.last()
    }

    /**
     * 打开activity
     */
    fun startActivity(cls:Class<out Activity>){
        var activity = getTopActivity()
        var intent = Intent(activity,cls)
        activity.startActivity(intent)
    }
    /**
     * 删除除最上层之外的所有activity
     */
    fun finishAllActivitesExceptTop(){
        var topActivity = getTopActivity()
        var iterator = activities.iterator()
        while (iterator.hasNext()){
            val activity = iterator.next()
            if (activity!=null) {
                if(activity != topActivity){
                    activity.finish()
                }
            }else{
                iterator.remove()
            }
        }
    }

    /**
     * 删除所有的activity
     */
    fun finishAllActivities(){
        var iterator = activities.iterator()
        while (iterator.hasNext()){
            val activity = iterator.next()
            activity?.finish()
            iterator.remove()
        }
    }
}