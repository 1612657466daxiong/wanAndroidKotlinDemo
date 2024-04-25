package com.chivers.kotlinwanandroid.livedatabus

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer


/**
 *
 *单例模式
 * 可以选择性去除livedata粘性数据
 */
object OKLiveDataBusKT {
    //存放订阅者
    private val bus:MutableMap<String, BusMutableLiveData<Any>> by lazy { HashMap() }


    @Synchronized
    fun <T> with(key:String,type:Class<T>,iStick:Boolean = true): BusMutableLiveData<T> {
        if(!bus.containsKey(key)){
            bus[key] = BusMutableLiveData(iStick)
        }
        return bus[key] as BusMutableLiveData<T>
    }

    class BusMutableLiveData<T> private constructor(): MutableLiveData<T>() {
        var isStick:Boolean = false
        constructor(isStick:Boolean):this(){
            this.isStick = isStick;
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            super.observe(owner, observer)
            if (isStick){//粘性数据
                hook(observer = observer)
            }else{//非粘性

            }
        }

        private fun hook(observer: Observer<in T>){
            //得到mLastVersion
            val liveDataClass = LiveData::class.java

            val mObserversField = liveDataClass.getDeclaredField("mObservers")
            mObserversField.isAccessible = true

            //获取这个成员变量的对象
            val mObserverObject = mObserversField.get(this);

            //得到map对象的class对象
            val mObserverClass = mObserverObject.javaClass

            //获取到mObserver对象的get方法 protected Entry<K,V> get(K k){}
            val get = mObserverClass.getDeclaredMethod("get", Any::class.java)
            get.isAccessible = true

            //执行get
            val invoke = get.invoke(mObserverObject, observer)

            var observerWraper:Any? = null
            if(invoke!=null && invoke is Map.Entry<*,*>){
                observerWraper = invoke.value
            }
            if(observerWraper ==null){
                throw NullPointerException("observerWraper is Null")
            }
            val superclass:Class<*> = observerWraper.javaClass.superclass
            val mLastVersion = superclass.getDeclaredField("mLastVersion")
            mLastVersion.isAccessible = true

            val mVersion = liveDataClass.getDeclaredField("mVersion")
            mVersion.isAccessible = true

            val mVersionValue = mVersion.get(this)
            mLastVersion.set(observerWraper,mVersionValue)

        }
    }

}